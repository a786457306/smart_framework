package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * StringUtil
 * 字符串工具类
 *
 *
 * @author Daydreamer
 * @date 2018/10/5 10:56
 */
public class StringUtil {

    public static boolean isEmpty(String str){
        if (str != null){
            // 忽略前导和尾部空白
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }


    public static String[] splitString(String body, String s) {
        return body.split(s);
    }
}

