package com.grapefruit.webtoken.utils;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/9/12 13:51:19
 */
public class StringUtils {

    /**
     * 处理token中的"."和"/"字符
     * @param str
     * @return
     */
    public static String strReplace(String str){
        return str.replace(".","");
    }
}
