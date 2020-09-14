package com.grapefruit.rsa.test;

import com.grapefruit.rsa.constant.Constant;
import com.grapefruit.rsa.util.IO;
import com.grapefruit.rsa.util.RSAUtils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

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
                generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr)));

        String sign = RSAUtils.sign(privateKey, text);

        RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance(Constant.RSA)
                .generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr)));

        //text = "2020GGG";
        boolean verify = RSAUtils.verify(text, publicKey, sign);
        System.out.println("verify:" + verify);
    }
}
