package com.bookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@ComponentScan({"com.bookshop.controller","com.bookshop.service","com.bookshop.entity.dto"})

public class SpringBootBookShopWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBookShopWebApplication.class, args);
    }
}