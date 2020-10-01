package com.grapefruit.getpost.controller;


import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/10/1 10:23:56
 */
@RestController
@RequestMapping("/post")
public class IndexController {

    @PostMapping(value = "/A",produces = MediaType.APPLICATION_JSON_VALUE)
    public String get(@RequestBody() String msg){
        return msg;
    }

    @PostMapping("/B")
    public Stu post(@RequestBody() Stu stu){
        return stu;
    }
}
@Data
class Stu{
    String name;
    int id;
}
