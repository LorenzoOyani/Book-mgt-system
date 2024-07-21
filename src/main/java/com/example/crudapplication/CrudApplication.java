package com.example.crudapplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulith;
import org.springframework.modulith.Modulithic;

@Modulithic
@SpringBootApplication(scanBasePackages = {"com.example.crudapplication"})
@Slf4j
public class CrudApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
