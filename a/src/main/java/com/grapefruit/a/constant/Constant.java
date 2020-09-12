package com.grapefruit.a.constant;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/9/12 20:04:54
 */
public class Constant {

    /**
     * RSA最大加密明文大小
     */
    public static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    public static final int MAX_DECRYPT_BLOCK = 128;

    //A 私钥
    public static final String A_PRIVATE_KEY = "apple";

    //A 公钥
    public static final String A_PUBLIC_KEY = "orange";

    //B 私钥
    public static final String B_PRIVATE_KEY = "grapefruit";

    //B 公钥
    public static final String B_PUBLIC_KEY = "banana";

    public static final String RSA = "RSA";

    public static final int KEY_SIZE = 1024;
}
