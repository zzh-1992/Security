package com.grapefruit.a.test;

import com.grapefruit.a.constant.Constant;
import com.grapefruit.a.util.IO;
import com.grapefruit.a.util.RSA;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/9/12 22:57:45
 */
public class SignAndVerify {

    public static void main(String[] args) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        String text = "123456Grapefruit";

        //获取io流获取私钥、公钥
        String privateKeyStr = IO.readPrivateKey();
        String publicKeyStr = IO.readPublicKey();

        RSAPrivateKey privateKey = (RSAPrivateKey)KeyFactory.
                getInstance(Constant.RSA).
                generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr)));

        String sign = RSA.sign(privateKey, text);

        RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance(Constant.RSA)
                .generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr)));

        text = "123456Grapefruit";
        boolean verify = RSA.verify(text, publicKey, sign);
        System.out.println("verify:" + verify);
    }
}
