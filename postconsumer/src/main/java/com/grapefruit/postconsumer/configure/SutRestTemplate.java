package com.grapefruit.postconsumer.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author 柚子苦瓜茶
 * @version 1.0
 * @ModifyTime 2020/7/28 14:52:11
 */
@Configuration
public class SutRestTemplate {

    /**
     * 设置负载平衡
     */
    //@LoadBalanced
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory){
        return new RestTemplate(clientHttpRequestFactory);
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(){

        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        /**
         * 设置连接超时时间
         */
        simpleClientHttpRequestFactory.setConnectTimeout(5*60*1000);

        return simpleClientHttpRequestFactory;
    }

    /**
     * 设置负载平衡策略-随机匹配
     * @return
     */
    //@Bean
    /*public IRule iRule(){
        //随机
        IRule iRule = new RandomRule();
        //轮询
        //IRule iRule = new ZoneAvoidanceRule();
        return iRule;

    }*/


}
