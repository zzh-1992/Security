package com.grapefruit.a.util;

import com.grapefruit.a.constant.Constant;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/9/12 19:23:16
 */
public class RSA {

    //网友博客地址:https://www.cnblogs.com/longyao/p/11346984.html

    //生成密钥
    public static PrivateKey  getPrivateKey(String basicPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //生成钥匙工厂
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        //生成密钥的解码字节数组
        byte[] decodedKey  = Base64.decodeBase64(basicPrivateKey);

        //私钥编码
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decodedKey);

        //生成密钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        return privateKey;
    }

    //生成公钥
    public static PublicKey getPublicKey(String basicPublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //生成钥匙工厂
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        //生成密钥的解码字节数组
        byte[] decodedKey  = Base64.decodeBase64(basicPublicKey);

        //公钥编码
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decodedKey);

        //生成公钥
        PublicKey publicKey = keyFactory.generatePublic(pkcs8EncodedKeySpec);
        return publicKey;
    }

    //随机生成密钥对
    public static Map<String,String> generateKeyPair() throws NoSuchAlgorithmException, IOException {
        //基于RSA算法生成密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(Constant.RSA);
        //初始化密钥对生成器(96-1024)
        keyPairGenerator.initialize(Constant.KEY_SIZE,new SecureRandom());
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //使用密钥对生成密钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //使用密钥对生成公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        //生成密钥字符串
        String privateKeyStr = new String(Base64.encodeBase64(privateKey.getEncoded()));
        //生成公钥字符串
        String publicKeyStr = new String(Base64.encodeBase64(publicKey.getEncoded()));
        Map<String,String> map = new HashMap<>();
        map.put("publicKey",publicKeyStr);
        map.put("privateKey",privateKeyStr);

        FileOutputStream fos = new FileOutputStream("private");
        fos.write(privateKeyStr.getBytes());
        fos.flush();
        fos.close();

        fos = new FileOutputStream("public");
        fos.write(publicKeyStr.getBytes());
        fos.flush();
        fos.close();

        return map;
    }

    /**
     * 解密
     *
     * @param cipherText 密文
     * @param key 私钥
     * @return 文本(密文解密的原文)
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws UnsupportedEncodingException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String decrypt(String cipherText,String key) throws NoSuchAlgorithmException, InvalidKeySpecException,
            IOException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //64位解码后的加密后字符串
        byte[] data = Base64.decodeBase64(cipherText.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decode = Base64.decodeBase64(key);

        RSAPrivateKey privateKey = (RSAPrivateKey)KeyFactory.
                getInstance(Constant.RSA).
                generatePrivate(new PKCS8EncodedKeySpec(decode));

        Cipher cipher = Cipher.getInstance(Constant.RSA);
        cipher.init(Cipher.DECRYPT_MODE,privateKey);

        //解密
        byte[] decryptedData = cipher.doFinal(data);

        String text = new String(decryptedData);

        return text;
    }

    /**
     * 文本加密
     *
     * @param key 公钥
     * @param text 文本
     * @return 密文(加密后的文本)
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String encrypt(String key,String text) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {

        //base64编码的公钥
        byte[] decodes = Base64.decodeBase64(key);

        RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(decodes));

        //生成密码对象
        Cipher cipher = Cipher.getInstance(Constant.RSA);
        //密码对象初始化(加密模式、公钥)
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);

        //把文本转换成byte数组
        byte[] data = text.getBytes("UTF-8");

        //加密(得到byte数组)
        byte[] cipherBytes = cipher.doFinal(data);

        //转化为字符串(密文)
        String cipherText = Base64.encodeBase64String(cipherBytes);

        return cipherText;
    }

    /**
     * 签名处理
     *
     * @param privateKey 公钥
     * @param data 文本(初始文本/原文)
     * @return 签名(文本进行签名处理的结果)
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static String sign(PrivateKey privateKey,String data) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(Constant.RSA);
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        //生成签名类对象
        Signature signature = Signature.getInstance("MD5withRSA");
        //初始化签名
        signature.initSign(key);
        //签名
        signature.update(data.getBytes());

        return new String(Base64.encodeBase64(signature.sign())) ;
    }

    /**
     * 验证签名处理
     *
     * @param srcData 原始文本
     * @param publicKey 公钥
     * @param sign 签名
     * @return 签名是否通过
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static boolean verify(String srcData,PublicKey publicKey,String sign) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(Constant.RSA);

        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");

        signature.initVerify(key);
        signature.update(srcData.getBytes());

        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }
}
