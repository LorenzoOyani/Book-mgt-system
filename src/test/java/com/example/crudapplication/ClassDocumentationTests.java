//package com.example.crudapplication;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.modulith.ApplicationModule;
//import org.springframework.modulith.core.ApplicationModules;
//import org.springframework.modulith.docs.Documenter;
//import org.springframework.modulith.test.ApplicationModuleTest;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
//@SpringBootTest
//@ApplicationModuleTest
//public class ClassDocumentationTests {
//
//    ApplicationModules applicationModules = ApplicationModules.of(CrudApplication.class);
//    @Test
//    void writeDocumentationSnippet(){
//      Documenter documenter = new Documenter(applicationModules);
//
//      try {
//          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//          PrintStream printStream = new PrintStream(outputStream);
//          documenter.writeModulesAsPlantUml();
//          System.out.println(outputStream.toString());
//
//          // Print individual modules as PlantUml to console
//          outputStream.reset();
//          documenter.writeIndividualModulesAsPlantUml();
//          System.out.println(outputStream.toString());
//      }catch (Exception e){
//          throw  new RuntimeException(e.getMessage());
//
//      }
//    }
//}
