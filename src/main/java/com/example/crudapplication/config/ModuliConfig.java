package com.example.crudapplication.config;

import com.example.crudapplication.CrudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@Configuration
public class ModuliConfig {

    @Bean
    public ApplicationModules applicationModules(){
     return   ApplicationModules.of(CrudApplication.class).verify();
    }

    @Bean
    public Documenter plantUMLDocumenter(ApplicationModules modules){
        Documenter documenter = new Documenter(modules);
        documenter.writeIndividualModulesAsPlantUml();

        return documenter;
    }


}
