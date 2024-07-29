package com.example.crudapplication;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.Map;

public class VariablesTest {


    @Getter
    @Setter
    public static class WriteAttendance{
        private String name;
    }

    @Test
   public void writeExpression(){
        var writeAttendance = new WriteAttendance();
        writeAttendance.setName("lyon");
        Assertions.assertEquals(writeAttendance.getName(), "lyon");
        var spelExpression = new SpelExpressionParser();
        var context = SimpleEvaluationContext.forReadWriteDataBinding().build();
        context.setVariable("newName", "lyon");
        spelExpression.parseExpression("name = #newName").getValue(context, writeAttendance);


    }

    @Test
    public void literalExpression(){
        var expressionParser=  new SpelExpressionParser();
        var message= expressionParser.parseExpression("'Hello, world'.concat('[').byte");
        var messageValue = message.getValue();
        if(messageValue instanceof byte[] bytes){
            Assert.assertEquals(new String(bytes), "Hello, world");
        }

    }
}
