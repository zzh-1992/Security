package com.grapefruit.webtoken.utils;

/**
 * @author Grapefruit
 * @version 1.0
 * @ModifyTime 2020/9/12 13:51:19
 */
public class StringUtils {

    /**
     * 处理token中的"."字符
     * @param str
     * @return 处理后的token字符串
     */
    public static String strReplace(String str){
        return str.replace(".","");
    }
}
