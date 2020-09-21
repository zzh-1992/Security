package com.grapefruit.pathvariable;

import org.springframework.web.bind.annotation.*;

/**
 *
 * show the difference between @PathVariable and @RequestParam
 *
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/9/21 19:45:16
 */
@RestController
@RequestMapping("/")
public class controller {

    /**e
     * localhost:8080/get/1/faaa
     */
    @GetMapping("/get/{id}/{age}")
    public String doSome(@PathVariable String id,@PathVariable String age){
        return "id:" + id + "  age:" + age;
    }

    /**
     * localhost:8080/get1?id=1&age=222
     */
    @GetMapping("/get1")
    public String doSome1(@RequestParam(name = "id") String id,@RequestParam(name = "age") String age){
        return "id:" + id + "  age:" + age;
    }
}
