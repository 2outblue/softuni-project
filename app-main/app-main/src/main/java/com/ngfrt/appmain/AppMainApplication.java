package com.ngfrt.appmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppMainApplication.class, args);
    }


}
