package com.grapefruit.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 柚子苦瓜茶  Grapefruit
 * @version 1.0
 * @ModifyTime 2020/10/14 07:17:44
 */
@RestController
public class Index {

    @GetMapping("/")
    public String toIndex(){
        return "hello";
    }
}
