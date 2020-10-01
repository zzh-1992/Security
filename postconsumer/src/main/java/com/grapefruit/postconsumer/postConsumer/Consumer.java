package com.grapefruit.postconsumer.postConsumer;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/10/1 10:50:06
 */
@RestController
@RequestMapping("/")
public class Consumer {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/g")
    public String post(){
        MultiValueMap<String, Stu> mapParams= new LinkedMultiValueMap<String, Stu>();
        mapParams.add("name",new Stu("ZhangZhihuang",1));
        String url = "localhost:9999/post/A";
        HttpHeaders headers = new HttpHeaders();
        HttpMethod post = HttpMethod.POST;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, Stu>> requestEntity = new HttpEntity<MultiValueMap<String, Stu>>(mapParams, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, post, requestEntity, String.class);
        System.out.println("post请求结果:" + exchange);
        return "";
    }

    @GetMapping("/post")
    public String postB(){


        return "";
    }
}


@Data
class Stu{
    String name;
    int id;

    public Stu(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Stu() {
    }
}
