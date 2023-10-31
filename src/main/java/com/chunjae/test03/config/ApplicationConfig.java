package com.chunjae.test03.config;

import com.chunjae.test03.per.UserMapper;
import com.chunjae.test03.per.UserMapperImpl;
import com.chunjae.test03.biz.UserService;

import com.chunjae.test03.biz.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.chunjae.test03"})
public class ApplicationConfig {


    @Bean
    public UserMapper userPersistence() {
        return new UserMapperImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }



}
