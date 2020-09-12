package com.grapefruit.webtoken.config.interceptor;

import com.grapefruit.webtoken.utils.StringUtils;
import com.grapefruit.webtoken.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/7/15 13:30
 */


@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //从请求头获取token
        String token = request.getHeader("token");

        System.out.println("请求头中的token:" + token);
        if(token == null | "".equals(token)){
            System.out.println("没有token");
            response.sendRedirect("/");
            return false;
        }
        //能到这的话说明请求头里有token,有的话就校验token
        boolean isTokenOk = TokenUtils.checkToken(token);
        System.out.println("isTokenOk:" + isTokenOk);

        if (isTokenOk){
            //判断缓存中是否有该token
            Boolean isTokenExist = redisTemplate.hasKey(StringUtils.strReplace(token));
            System.out.println("isTokenExist:" + isTokenExist);
            if(!isTokenExist){
                System.out.println("判断redis中没有token");
                return false;
            } else {
                System.out.println("判断redis中有token");
                return true;
            }
        } else {
            System.out.println("token无效");
            response.sendRedirect("/");
            return false;
        }

        /*
        cookie校验
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null){
            response.sendRedirect("/");
            System.out.println("Redirecting=========>/");
            return false;
        }*/
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
