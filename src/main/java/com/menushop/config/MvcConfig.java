package com.menushop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path.cafe}")
    private String uploadPathAuthor;

    @Value("${upload.path.dish}")
    private String uploadPathBook;
    @Value("${upload.path.dish.not}")
    private String uploadPathBookNot;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/author/**")
                .addResourceLocations("file:"+uploadPathAuthor+"/");
        registry.addResourceHandler("/img/book/**")
                .addResourceLocations("file:" + uploadPathBook + "/");
        registry.addResourceHandler("/img/bookNot/**")
                .addResourceLocations("file:/" + uploadPathBookNot + "/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
