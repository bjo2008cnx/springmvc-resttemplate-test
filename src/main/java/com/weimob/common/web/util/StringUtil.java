package com.weimob.common.web.util;

/**
 * @author Michael.Wang
 * @date 2016/11/24
 */
public class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String quote(String str) {
        return "\"".concat(str).concat("\"");
    }

    public static boolean startWith(String str, String... toChecks) {
        for (int i = 0; i < toChecks.length; i++) {
            if (str.toLowerCase().startsWith(toChecks[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean endWith(String str, String... toChecks) {
        for (int i = 0; i < toChecks.length; i++) {
            if (str.toLowerCase().endsWith(toChecks[i])) {
                return true;
            }
        }
        return false;
    }
}
