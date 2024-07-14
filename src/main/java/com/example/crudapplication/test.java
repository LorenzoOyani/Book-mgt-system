//package com.example.crudapplication;
//
//import com.example.crudapplication.book.Book;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.jdbc.core.simple.JdbcClient;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.function.BiFunction;
//
//@Component
//public class test {
//
//    private final JdbcClient jdbcClient;
//
//    @Autowired
//    public test(JdbcClient jdbcClient) {
//        this.jdbcClient = jdbcClient;
//    }
//
//    public static void main(String[] args) {
//        List<Integer> values = Arrays.asList(1, 3, 4, 5);
//        List<String> keys = Arrays.asList("a", "b", "c", "d");
//
//        BiFunction<String, Integer, String> function = (k, v) -> k + "=" + v;
//        Map<String, Integer> maps = new HashMap<>();
//        for (int i = 0; i < keys.size(); i++) {
//            maps.put(keys.get(i), values.get(i));
//        }
//        maps.forEach((k, v) -> System.out.println(function.apply(k, v)));
//    }
//
//    public static ApplicationRunner runner(JdbcClient jdbcClient) {
//        return args -> {
//            jdbcClient.sql("select * from Book.class")
//                    .query(Book.class)
//                    .stream()
//                    .forEach(book -> System.out.println("book " + book.toString()));
//
//        };
//
//    }
//
//
//}
