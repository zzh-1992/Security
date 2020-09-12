package com.grapefruit.webtoken.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/9/9 19:21:10
 */
public class TokenUtils {

    //过期时间(30分钟)
    public static final long EXPIRE_Time = 30 * 60 * 1000;
    //public static final long EXPIRE_Time = 60 * 1000;

    public static final String TOKEN_SECRET = "d621d333dec745fd8d44ad38428714de";

    public static void main(String[] args) throws InterruptedException {

        //调用方法生成token
        String token = createToken();
        Thread.sleep(2 * 1000);

        //判断token是否有效
        boolean isOk = checkToken(token);

        //System.out.println(isOk?"token有效":"token过期");

        getContentFromToken(token);
    }

    //生成token密钥(使用uuid生成的32位字符串当作token密钥)
    public static String createUUID() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println(uuid);
        return uuid;
    }

    /**
     * 生成token
     *
     * @return 返回token字符串
     */
    public static String createToken() {
        //设置过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_Time);

        //设置算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

        //设置参数
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");

       //生成token
        String token = JWT.create()
                .withHeader(header)
                .withClaim("userName", "Grapefruit")
                .withClaim("password", "123")
                .withExpiresAt(date)
                .sign(algorithm);
        System.out.println("生成的token:" + token);
        return token;
    }

    /**
     * 校验toke
     *
     * @param token
     * @return 判断token是否有效
     */
    public static boolean checkToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    //解析token中的信息(userName、password)
    public static String getContentFromToken(String token) {
        DecodedJWT decode = JWT.decode(token);
        String userName = decode.getClaim("userName").asString();
        String password = decode.getClaim("password").asString();
        System.out.println("解析的用户名:" + userName);
        System.out.println("解析的密码:" + password);
        return "";
    }
}
