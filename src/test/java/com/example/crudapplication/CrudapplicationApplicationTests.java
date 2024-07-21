package com.example.crudapplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.test.ApplicationModuleTest;

@ApplicationModuleTest
class CrudApplicationApplicationTests {

    @Test
    void contextLoads() {

        var modules = ApplicationModules.of(CrudApplication.class).verify();
        modules.forEach(System.out::println);

        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }

}
