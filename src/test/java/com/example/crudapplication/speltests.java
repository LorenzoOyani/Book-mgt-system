package com.example.crudapplication;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.*;


public class speltests {

    public record Inventory(String name, Date birthdate, String nationality, String[] inventories){}
    public static Inventory Tesla = new Inventory("Nikola Tesla", new GregorianCalendar(1897, Calendar.AUGUST, 9).getTime(), "serbian", new String[]{});


    @Test
    void arrayAndCollections(){
        var expressionParser = new SpelExpressionParser();

        // class access
        var expressionParsers = expressionParser.parseExpression("Inventory[0].toUpperCase");
        var result = expressionParsers.getValue(Tesla, String.class);
        Assertions.assertEquals(result, "induction motor".toUpperCase());

        // arrayLiteral access
        var array = expressionParser.parseExpression("new int[1, 2, 3, 4]");
        var newArr = array.getValue(int[].class);
        Assertions.assertNotNull(newArr);
        Assertions.assertEquals(newArr.length, 4);

        //list literal
        var numberList = expressionParser.parseExpression("{0, 1, 2, 3, 4}")
                .getValue(List.class);
        Assertions.assertNotNull(numberList, "number cannot be null");
        for(var i = 0; i < numberList.size(); i++){
            Assertions.assertTrue(numberList.contains(i));

        }

        // map assertions
        var maps = expressionParser.parseExpression("{name: bob}")
                .getValue(Map.class);
        Assertions.assertNotNull(maps, "map keys cannot be null");
        Assertions.assertTrue(maps.containsKey("name") && maps.get("name").equals("bob"));



// String tests
        Assertions.assertEquals(expressionParser.parseExpression("abed.substring(2,4)").getValue(String.class), "bed");

        //Boolean expression
        Assertions.assertEquals(expressionParser.parseExpression("2 == 2").getValue(java.lang.Boolean.class), true);
        Assertions.assertEquals(expressionParser.parseExpression("1 > 2").getValue(Boolean.class), false);



    }
}
