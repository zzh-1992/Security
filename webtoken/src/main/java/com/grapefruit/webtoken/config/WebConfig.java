package com.grapefruit.webtoken.config;

import com.grapefruit.webtoken.config.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * @author Grapefruit
 * @version 1.0
 * @ModifyTime 2020/7/15 15:06
 */

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").
                excludePathPatterns("/","/login","/static/**","/templates/**","/images/**");
        //registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
    }
}
