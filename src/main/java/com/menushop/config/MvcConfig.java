package com.menushop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path.cafe}")
    private String uploadPathCafe;

    @Value("${upload.path.dish}")
    private String uploadPathDish;
    @Value("${upload.path.dish.not}")
    private String uploadPathDishNot;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/cafe/**")
                .addResourceLocations("file:"+ uploadPathCafe +"/");
        registry.addResourceHandler("/img/dish/**")
                .addResourceLocations("file:" + uploadPathDish + "/");
        registry.addResourceHandler("/img/dishNot/**")
                .addResourceLocations("file:/" + uploadPathDishNot + "/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
