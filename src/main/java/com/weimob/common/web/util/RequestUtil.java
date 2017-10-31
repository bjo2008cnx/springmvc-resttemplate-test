package com.weimob.common.web.util;

import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;

/**
 * request工具类
 *
 * @author Miao
 */
@Log4j2
public class RequestUtil {
    public static final String ACCEPT = "accept";
    public static final String CONTENT_TYPE = "content-type";
    public static final String APPLICATION_JSON = "application/json";

    public static String getHost(HttpServletRequest request){
        StringBuffer requestURL = request.getRequestURL();
        StringBuffer urlBuffer = requestURL.delete(requestURL.length() - request.getRequestURI().length(), requestURL.length()).append("/");
        return urlBuffer.toString();
    }

    /**
     * 获取请求路径
     *
     * @param request
     * @return
     */
    public static String getRequestPath(HttpServletRequest request) {
        String url = request.getServletPath();

        if (request.getPathInfo() != null) {
            url += request.getPathInfo();
        }

        return url;
    }

    /**
     * 是否ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        if(contentType == null){
            return false;
        }
        if (contentType.contains(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
            return false;
        } else if (contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
            return true;
        } else if (contentType.contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {
            return false;
        } else {
            return false;
        }
    }
}
