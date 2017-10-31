package com.weimob.common.web.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weimob.common.constants.O2OCommonConstant;
import com.weimob.common.constants.ServiceIndustryEnum;
import com.weimob.common.enums.RETURN_CODE;
import com.weimob.common.risk.RiskControlService;
import com.weimob.common.web.util.RequestValueUtil;
import com.weimob.common.web.util.StringUtil;
import com.weimob.utility.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * RiskControlFilter
 *
 * @author Michael.Wang
 * @date 2017/9/8
 */
@Log4j2
public class RiskControlFilter implements Filter {
    private static final String ERROR_PAGE = "/risk_error.html"; //需要放到assets下，否则需要登录
    public static final String ERROR_MESSAGE = "您的输入可能包含敏感信息，请修改后重试。";
    protected static final String SESSION_ID_COOKIE = O2OCommonConstant.CURRENT_ENV+"_osessionid";
    public static final String DEFAULT_REDIS_CLIENT_NAME = "weimobSimpleRedisClient";
    private static final String WHITE_LIST_WORDS_KEY = "WHITE_LIST_WORDS";
    private static final String REDIS_ONLY_CHECK_FREE = "onlyCheckFree";
	private static final String RISK_OFF_KEY = "RISK_OFF";

    public static final int FREE_SUITE_ID = ServiceIndustryEnum.TRY_SERVICE.getKey();
    public static boolean ONLY_CHECK_FREE = true;
    private String redisClientBeanName;

    /**
     * 需要排除的页面
     */
    private String excludedPages;
    private SortedSet<String> excludePath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            excludedPages = filterConfig.getInitParameter("excludedPages");
            excludePath = new TreeSet<String>();
            if (StringUtil.isNotEmpty(excludedPages)) {
                excludePath.addAll(Arrays.asList(excludedPages.split(",")));
            }
            log.info("exclude pages:" + excludedPages.toString());
            log.info("exclude path: {}", excludePath.toString());
        } catch (Throwable t) {
            log.error("fail to load exclude pages config from web.xml.", t);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("start to check Ip limit.");

        try {
            String path = req.getServletPath();
            log.debug("path: {}", path);

            if ("GET".equals(req.getMethod())) {
                log.debug("is GET. ignored.");
                chain.doFilter(request, response);
                return;
            }
            String contentType = req.getHeader(HttpHeaders.CONTENT_TYPE);
            log.debug("contentType: " + contentType);
            if (contentType != null && contentType.contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {
                log.debug("is multipart request.ignored.");
                chain.doFilter(request, response);
                return;
            }

            String requestBody = RequestValueUtil.parseRequestValues(request);
            log.debug("uri is : {}, requestBody to filter is : {}", ((HttpServletRequest) request).getRequestURI(), requestBody);

            if (StringUtils.isEmpty(requestBody)) {
                log.debug("requestBody is empty");
                chain.doFilter(request, response);
                return;
            }

            HttpServletResponse res = (response instanceof HttpServletResponse) ? (HttpServletResponse) response : null;
            boolean validateResult = false;
            try {
                validateResult = RiskControlService.qualityRiskControl(requestBody, getRealClientIp(req));
            } catch (Exception e) {
                log.error("call risk interface error,", e);
            }
            log.info("risk control result is: " + validateResult);
            if (!validateResult && res != null) {
                String acceptHeader = ((HttpServletRequest) request).getHeader(HttpHeaders.ACCEPT);
                if (StringUtils.isNotEmpty(acceptHeader)) {
                    List<MediaType> mediaTypes = MediaType.parseMediaTypes(acceptHeader);
                    log.debug("mediaTypes:" + JSON.toJSONString(mediaTypes));
                    MediaType.sortBySpecificityAndQuality(mediaTypes);
                    MediaType mediaType = mediaTypes.get(0);
                    log.debug("mediaType:" + JSON.toJSONString(mediaType));
                    if (mediaType.isCompatibleWith(MediaType.APPLICATION_JSON)) {
                        handleAjax((HttpServletRequest) request, response, path);
                        return;
                    }
                }
                log.info("redirecting.........");
                res.sendRedirect(((HttpServletRequest) request).getContextPath() + ERROR_PAGE);
                return;
            } else {
                chain.doFilter(request, response);
                return;
            }
        } catch (Throwable e) {
            log.error("fail to filter", e);
            chain.doFilter(request, response);
        }
    }

    private String getRealClientIp(HttpServletRequest request) {
        String realIp = request.getHeader("real-ip");
        if(StringUtils.isEmpty(realIp)){
            realIp = request.getHeader("x-real-ip");
        }
        return  realIp;
    }

    private void handleAjax(HttpServletRequest request, ServletResponse response, String path) throws IOException {
        JSONObject json = new JSONObject();
        if (StringUtils.isNotEmpty(path) && path.contains("offline-event") && path.contains("h5")) {
            json.put("status", 1);
        } else {
            json.put("status", RETURN_CODE.FAIL.getCode());
        }
        json.put("errCode", RETURN_CODE.FAIL.getCode());
        json.put("msg", ERROR_MESSAGE);
        json.put("message", ERROR_MESSAGE);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json.toJSONString());
    }

    @Override
    public void destroy() {

    }
}