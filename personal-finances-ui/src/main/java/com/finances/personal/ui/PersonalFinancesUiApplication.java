package com.finances.personal.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
@EnableZuulProxy
public class PersonalFinancesUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalFinancesUiApplication.class, args);
    }
    
}