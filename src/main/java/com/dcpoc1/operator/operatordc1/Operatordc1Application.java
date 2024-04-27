package com.dcpoc1.operator.operatordc1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Operatordc1Application {


    public static void main(String[] args) {
        // Set the context class loader to the current class loader
        Thread.currentThread().setContextClassLoader(Operatordc1Application.class.getClassLoader());
        // Run the Spring Boot application
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "DEBUG");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.wire", "ERROR");

        System.out.println("Coming from spring boot");

        SpringApplication.run(Operatordc1Application.class, args);
    }
}
