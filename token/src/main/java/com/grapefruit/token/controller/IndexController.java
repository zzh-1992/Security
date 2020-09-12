package com.grapefruit.token.controller;

/**
 * @author 柚子苦瓜茶  token测试
 * @version 1.0
 * @ModifyTime 2020/9/9 19:48:57
 */

import com.grapefruit.token.entity.Stu;
import com.grapefruit.token.utils.TokenTest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/grapefruit", method = RequestMethod.GET)
public class IndexController {

    /**
     * url localhost:8080/grapefruit/login
     * <p>
     * {
     * "userName":"grapefruit",
     * "password":"123"
     * }
     * <p>
     * Headers
     * contentType application/json
     */
    @RequestMapping("/login")
    public String login(@RequestBody Stu stu) {

        if (!stu.getUserName().equals("grapefruit")) {
            return "没有该用户";
        }
        if (!stu.getPassword().equals("123")) {
            return "密码错误";
        } else {
            String token = TokenTest.sign();
            return token;
        }
    }

    //后台
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        String headerToken = request.getHeader("token");

        System.out.println("index token:" + headerToken);

        boolean isLogin = TokenTest.checkToken(headerToken);

        if (isLogin) {
            return "会话状态持续";
        } else {
            return "会话已经失效";
        }
    }
}
