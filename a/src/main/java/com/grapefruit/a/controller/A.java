package com.grapefruit.a.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/9/12 19:10:31
 */
@RestController
public class A {

    @GetMapping("/")
    public String toB(@RequestParam String msg){

        return "";
    }
}
