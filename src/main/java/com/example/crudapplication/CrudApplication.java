package com.example.crudapplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulith;
import org.springframework.modulith.Modulithic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Modulithic
@SpringBootApplication(scanBasePackages = {"com.example.crudapplication"})
@Slf4j
public class CrudApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> list=new ArrayList<>(Arrays.asList("one","two"));
        Stream<String> sl = list.stream();
        list.add("three");
        String  s = sl.collect(Collectors.joining(","));

        final Integer[] numbers = {1, 2, 3, 4, 5, 6};
        Arrays.stream(numbers).reduce(0, Integer::sum);

    }
}
