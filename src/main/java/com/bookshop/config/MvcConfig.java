package com.bookshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path.author}")
    private String uploadPathAuthor;

    @Value("${upload.path.book}")
    private String uploadPathBook;
    @Value("${upload.path.book.not}")
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
