package com.grapefruit.a.util;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/9/12 21:36:07
 */
public class IO {

    /**
     * 从外部文件中读取公钥
     * @return 公钥字符串
     * @throws IOException
     */
    public static String readPublicKey() throws IOException {

        FileInputStream fis = new FileInputStream("public");
        int readCount;
        String publicKeyStr = "";
        byte[] bytes = new byte[1024];
        while((readCount = fis.read(bytes)) != -1){
            publicKeyStr = new String(bytes, 0, readCount);
        }
        fis.close();
        return publicKeyStr;
    }

    /**
     * 从外部文件中读取密钥
     * @return 密钥字符串
     * @throws IOException
     */
    public static String readPrivateKey() throws IOException {

        FileInputStream fis = new FileInputStream("private");
        int readCount;
        String publicKeyStr = "";
        byte[] bytes = new byte[1024];
        while((readCount = fis.read(bytes)) != -1){
            publicKeyStr = new String(bytes, 0, readCount);
        }
        fis.close();
        return publicKeyStr;
    }
}
