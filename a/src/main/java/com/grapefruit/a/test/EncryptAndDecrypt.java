package com.grapefruit.a.test;

import com.grapefruit.a.util.IO;
import com.grapefruit.a.util.RSA;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/9/12 20:42:19
 */
public class EncryptAndDecrypt {

    //A 私钥
    public static String A_PRI = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ4kGbSjqrYL0RoKKri257AtbxyE93FELyP8pL++0erF+nWJF0O6X1Rh1JHX8av5HuM9dPeOVQq78e3W10eu/MsDhrxkB0SEua/Cy9fnib2D2sf6FT6uuTr9CSNvfWtuNiGdJgMYELmhnigtXfn08Hjio2clB2HpcmhR1/BLs7jfAgMBAAECgYBOwg3nT+eSGJqADHYwXi9PJ6GBo5sUVu/fTVDDigZDlWviI29ptKULgll6dQCQNFa0t4PMUp955BZd7DR1J+oPaC7CWMI2e03+nppF+0CadP9//TRT+fo6LJSzBLe3A8i+gsNXX8Ta+7lzINfEPO+F27fRuGui/zdza+ellMXR6QJBAPpbxlwLw6BRDZZbVbA8rEaY8E1vVkNFEV+rj0VpI3FOpg0e11rEXQtE+OMJuc4MxHUUkY7oCPerWTvFYKgIJl0CQQChtFtxJl+d1o8cCC/pTUoLTZWIlOzxZr/LGezIjoe6JK6mgB1alGmyZpcYXBnm77r1qKS4ZSx7QrhwCRcfX3BrAkEAn52ZZp/ddj2W0xCiHf1gvJXPRx8/uR20yPEBI/i3Lws8lMrf9C+TPpcXsvBFdNfw/NBDSQAoj3bAKnpZeGfRxQJBAJJ3S5AsgtTzf8KTQ9pc8GFWj8gbpJZvS++J9ugd+aBIE42qY2wlG6vEsr0Uxz1fDQfwDesbcPPgljCcez4mcysCQBM2znBaxpyU2fioFYY6Or30+xD/na4pFqPAcMVWV92gE61vjfYysYvMZdD90o2/MP2i/3FK2C3litA3LK+hwu8=";
    //A 公钥
    public static String A_PUB = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCeJBm0o6q2C9EaCiq4tuewLW8chPdxRC8j/KS/vtHqxfp1iRdDul9UYdSR1/Gr+R7jPXT3jlUKu/Ht1tdHrvzLA4a8ZAdEhLmvwsvX54m9g9rH+hU+rrk6/Qkjb31rbjYhnSYDGBC5oZ4oLV359PB44qNnJQdh6XJoUdfwS7O43wIDAQAB";

    //B 私钥
    public static String B_PRI = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMP4iAaHm2mEvzao5zj5YwRqsOCp5+5MtbXa9m6fq7WaJ8XS9KdVsvgWUfUehpepemSeuTvVkP6/mRnDYqGUpM77kDbSS1XLFxS11dnA/yrYK2zMqZxuonIhOUHnrc8/Kw+b2Qj5mWOZMPgYctsoh0xJ4C+9bi0z42XOY5bqXZc9AgMBAAECgYAZ4imVMzQDq9TVk1l23PmHC9Bw3AAWKWuWqWUDF3PGeKXoKwTFVTxeKBSLG40LWwcfYxd0H7EW9Jd0H6pXTRq3QLbUyPmGMBO6GLsnRwVQe0oF2l7UyY6Tb4EThGrUH6URzQzv2mUEwC1TZSA8/klsxuXgoNW6cImcAKnMIxMkAQJBAOKb7e+awRnPP7Fw090kYgvG3Q7A6Q4i43kroftIJ4fLjnCAiNnA3a0soymR4U1mBTI542Q1d70SNEFcGu5t/g0CQQDdY1LUEhWXb0eiwrdGYWyBykPmdyh7Xv7ccdpv/rf37eTzi7KKK9TK1OgGbmZDCy7zIobTl3z34WJ1q8dQqOHxAkEAm4WMjK7W7BGykuLuZI09xPtcZ226BPuoxlZNuxlQGAB553gvc9LL0Lgb2sHFsMrdshTMnM+dN+OwfVLrIMvYWQJBAJC9YiayWBl0sJIR2b8S7N0CP1hK6VWXaur3KMf18ZMPnpM37xd0xp+BiNzDiuokQqLFHP7zWMm7OiMSlrH+aBECQFBnlmx36dHvRPLAt2yliDKqFvEM0Pyh38Ak/xNFV6N8Fb22+MShpeV0Bv1qdmRl2kc8g80TkPVa4zT5RGKxKSs=";
    //B 公钥
    public static String B_PUB = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDD+IgGh5tphL82qOc4+WMEarDgqefuTLW12vZun6u1mifF0vSnVbL4FlH1HoaXqXpknrk71ZD+v5kZw2KhlKTO+5A20ktVyxcUtdXZwP8q2CtszKmcbqJyITlB563PPysPm9kI+ZljmTD4GHLbKIdMSeAvvW4tM+NlzmOW6l2XPQIDAQAB";

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {

        //生成随机密钥对
        /*Map<String, String> map = RSA.generateKeyPair();
        B_PRI = map.get("privateKey");
        System.out.println(B_PRI);
        B_PUB  = map.get("publicKey");
        System.out.println(B_PUB);
        String text = "1234Grapefruit";*/

        RSA.generateKeyPair();
        String text = "123456Grapefruit==666=====";
        //公钥加密,私钥解密
        String encrypt = RSA.encrypt(IO.readPublicKey(), text);
        System.out.println("密文:" + encrypt);
        System.out.println(RSA.decrypt(encrypt,IO.readPrivateKey()));
    }
}
