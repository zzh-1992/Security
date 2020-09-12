package com.grapefruit.webtoken.controller;

import com.grapefruit.webtoken.utils.StringUtils;
import com.grapefruit.webtoken.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/9/12 11:38:09
 */

@RestController
public class IndexController {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    //首页
    @GetMapping("/")
    public String index(){
        return "HomePage";
    }

    //登陆
    @GetMapping("/login")
    public String login(@RequestParam(value = "name") String name,
                        @RequestParam(value = "password") String password,
                        HttpServletResponse response){
        if("zzz".equals(name) && "123".equals(password)){
            //登陆成功就生成token并写入响应头
            String token = TokenUtils.createToken();
            System.out.println("登陆成功生成的token:");
            System.out.println();
            System.out.println(token);
            response.addHeader("token",token);

            //把token放入redis缓存
            ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(StringUtils.strReplace(token),token,30, TimeUnit.MINUTES);

            return "登陆成功";
        } else {
            return "登陆失败";
        }
    }

    //后台页面
    @GetMapping("/backstage")
    public String backstage(){
        return "这里是后台" + new SimpleDateFormat("yyyyMMdd HH:mm:ss SSS").format(new Date());
    }

    //退出
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){

        String token = request.getHeader("token");
        //删除token
        redisTemplate.delete(StringUtils.strReplace(token));
        return "退出系统";
    }
}
