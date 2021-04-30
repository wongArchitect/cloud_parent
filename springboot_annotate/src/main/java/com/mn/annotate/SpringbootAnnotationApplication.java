package com.mn.annotate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringbootAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAnnotationApplication.class, args);
    }

}
