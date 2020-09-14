package com.grapefruit.token.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/9/9 19:21:10
 */
public class TokenTest {

    public static void main(String[] args) throws InterruptedException {

        //调用方法生成token
        String token = TokenUtils.generateToken();
        Thread.sleep(2 * 1000);

        //判断token是否有效
        boolean isOk = TokenUtils.checkToken(token);

        System.out.println(isOk?"token有效":"token过期");

        TokenUtils.getContentFromToken(token);
    }



}
