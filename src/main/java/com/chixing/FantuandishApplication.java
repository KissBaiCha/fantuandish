package com.chixing;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ZhangJiuJiu
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class FantuandishApplication {
    public static void main(String[] args) {
        SpringApplication.run(FantuandishApplication.class, args);
    }

}
