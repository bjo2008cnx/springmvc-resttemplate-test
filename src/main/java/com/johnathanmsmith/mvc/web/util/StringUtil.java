package com.johnathanmsmith.mvc.web.util;

/**
 * 一些通用的字符串处理
 */
public class StringUtil {

    /**
     * 加双引号
     *
     * @param str
     * @return
     */
    public static String quote(String str) {
        return "\"".concat(str).concat("\"");
    }

    /**
     * 检查这个字符串是不是空字符串。<br/>
     * 如果这个字符串为null或者trim后为空字符串则返回true，否则返回false。
     *
     * @param str 被检查的字符串
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return isNullOrEmpty(str);
    }

    /**
     * 检查这个字符串是不是空字符串。<br/>
     * 如果这个字符串为null或者trim后为空字符串则返回true，否则返回false。
     *
     * @param chkStr
     * @return
     */
    public static boolean isNullOrEmpty(String chkStr) {
        if (chkStr == null) {
            return true;
        } else {
            return "".equals(chkStr.trim()) ? true : false;
        }
    }
}