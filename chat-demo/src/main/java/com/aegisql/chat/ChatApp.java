package com.aegisql.chat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ChatApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ChatApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
