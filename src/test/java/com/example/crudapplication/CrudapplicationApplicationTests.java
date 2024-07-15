package com.example.crudapplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.test.ApplicationModuleTest;

@SpringBootTest
@ApplicationModuleTest
class CrudApplicationApplicationTests {

    @Test
    void contextLoads() {

        var modules = ApplicationModules.of(CrudApplication.class).verify();

        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }

}
