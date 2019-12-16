package com.mountain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mountain.*",})
public class Server {
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
