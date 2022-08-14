package com.baranbatur.vetApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class VetAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(VetAppApplication.class, args);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String rawPass = encoder.encode("admin");
//        System.out.println(rawPass);
    }

}
