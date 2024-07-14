package com.example.crudapplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.modulith.Modulith;

@SpringBootApplication
@Slf4j
public class CrudApplicationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplicationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
