package com.weimob.common.web.filter;

import com.weimob.common.web.context.RepeatedlyReadRequestWrapper;
import com.weimob.common.web.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//TODO 系统稳定之后改info为debug
@Slf4j
public class RepeatlyReadFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("RepeatlyReadFilter [start]");
        try {
            if (request instanceof HttpServletRequest) {
                log.info("request is HttpServletRequest");
                HttpServletRequest req = (HttpServletRequest) request;
                String contentType = req.getHeader(HttpHeaders.CONTENT_TYPE);
                log.info("contentType: " + contentType);
                if (contentType == null || contentType.contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {
                    // 表单提交. do nothing
                } else if (RequestUtil.isAjaxRequest(req)) {
                    log.info("is ajax request.");
                    if (!(request instanceof RepeatedlyReadRequestWrapper)) {
                        request = new RepeatedlyReadRequestWrapper(req);
                    }
                }
            }
        } catch (Throwable e) {
            log.error("fail to filter in RepeatlyReadFilter", e);
        }
        log.info("RepeatlyReadFilter [end]");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //Do nothing
    }

}
