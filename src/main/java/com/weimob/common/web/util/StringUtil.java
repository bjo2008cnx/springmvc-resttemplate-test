package com.weimob.common.web.util;

import java.util.Collection;

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

    /**
     * 把string array or list用给定的符号symbol连接成一个字符串
     *
     * @param list   需要处理的列表
     * @param symbol 链接的符号
     * @return 处理后的字符串
     */
    public static String join(Collection list, String symbol) {
        String result = "";
        if (list != null) {
            for (Object o : list) {
                if (o == null) continue;
                String temp = o.toString();
                if (temp.trim().length() > 0) result += (temp + symbol);
            }
            if (result.length() > 1) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    /**
     * 功能: 把new String[]{"abc", null, "123"}转化为 "abc,123"
     *
     * @param arrayString
     * @param spliter
     * @return
     */
    public static String join(char spliter, String... arrayString) {
        StringBuilder sb = new StringBuilder();
        if (arrayString == null || arrayString.length == 0) {
            return null;
        }
        for (int i = 0; i < arrayString.length; i++) {
            if (arrayString[i] != null && arrayString[i].length() > 0) {
                if (sb.length() > 0) {
                    sb.append(spliter);
                }
                sb.append(arrayString[i]);
            }
        }
        return sb.toString();
    }
}
