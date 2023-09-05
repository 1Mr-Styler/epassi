package com.jerry.epassi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EpassiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpassiApplication.class, args);
    }

}
