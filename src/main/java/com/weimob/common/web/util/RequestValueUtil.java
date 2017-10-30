package com.weimob.common.web.util;

import com.weimob.common.util.JSONUtil;
import com.weimob.common.web.context.RepeatedlyReadRequestWrapper;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * request工具类
 *
 * @author Miao
 */
@Log4j2
public class RequestValueUtil {
    /**
     * 解析请求的body
     *
     * @param req
     * @return
     */
    public static String parseRequestValues(ServletRequest req) {
        Map jsonMap = null;
        Map requestMap = null;
        //判断是否是包装过的request
        if (req instanceof RepeatedlyReadRequestWrapper) {
            RepeatedlyReadRequestWrapper request = (RepeatedlyReadRequestWrapper) req;
            jsonMap = JSONUtil.fromJson(request.getBody(), Map.class);
            requestMap = parseQuery(request.getQueryString());
        } else {
            HttpServletRequest request = (req instanceof HttpServletRequest) ? (HttpServletRequest) req : null;
            if (request != null) {
                jsonMap = request.getParameterMap();
                requestMap = parseQuery(request.getQueryString());
            }
        }

        requestMap.putAll(jsonMap);
        String requestBody = parseValue(requestMap);
        if (StringUtil.isEmpty(requestBody)) {
            return null;
        }
        try {
            requestBody = URLDecoder.decode(requestBody, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return requestBody;
    }

    /**
     * 解析 value,过滤之后以逗号分隔
     *
     * @param map
     * @return
     */
    public static String parseValue(Map<String, Object> map) {
        Iterator<String> names = map.keySet().iterator();
        StringBuilder builder = new StringBuilder();
        while (names.hasNext()) {
            String key = names.next().toString();
            if (filterIdAndToken(key)) {
                continue;
            }
            Object value = map.get(key);
            builder.append(StringUtil.quote(value.toString()));
            if (names.hasNext()) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    /**
     * 解析字符串返回map键值对(例：a=1&b=2 => a=1,b=2)
     *
     * @param query
     * @return
     */
    public static Map<String, String> parseQuery(String query) {
        return parseQuery(query, '&', '=', null);
    }

    /**
     * 解析字符串返回map键值对(例：a=1&b=2 => a=1,b=2)
     *
     * @param query   源参数字符串
     * @param split1  键值对之间的分隔符（例：&）
     * @param split2  key与value之间的分隔符（例：=）
     * @param dupLink 重复参数名的参数值之间的连接符，连接后的字符串作为该参数的参数值，可为null
     *                null：不允许重复参数名出现，则靠后的参数值会覆盖掉靠前的参数值。
     * @return map
     */
    public static Map<String, String> parseQuery(String query, char split1, char split2, String dupLink) {
        if (!StringUtil.isEmpty(query) && query.indexOf(split2) > 0) {
            Map<String, String> result = new HashMap<>();
            String name = null;
            String value = null;
            int len = query.length();
            for (int i = 0; i < len; i++) {
                char c = query.charAt(i);
                if (c == split2) {
                    value = "";
                } else if (c == split1) {
                    handleDupLink(dupLink, result, name, value);
                    name = null;
                    value = null;
                } else if (value != null) {
                    value += c;
                } else {
                    name = (name != null) ? (name + c) : "" + c;
                }
            }
            handleDupLink(dupLink, result, name, value);
            return result;
        }
        return null;
    }

    private static void handleDupLink(String dupLink, Map<String, String> result, String name, String value) {
        String tempValue;
        if (!StringUtil.isEmpty(name) && value != null) {
            if (dupLink != null) {
                tempValue = result.get(name);
                if (tempValue != null) {
                    value += dupLink + tempValue;
                }
            }
            result.put(name, value);
        }
    }

    public static boolean filterIdAndToken(String element) {
        String[] starts = {"id", "token", "lat", "lng", "mapLocation"};
        String[] ends = {"id", "token", "no", "num", "number"};
        return StringUtil.startWith(element, starts) || StringUtil.endWith(element, ends);
    }
}
