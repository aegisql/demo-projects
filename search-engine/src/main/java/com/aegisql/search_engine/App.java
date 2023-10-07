package com.aegisql.search_engine;

import com.aegisql.conveyor.Conveyor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aegisql.conveyor.config.ConveyorConfiguration;

@SpringBootApplication
public class App implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Conveyor.loadServices();
    }
}
