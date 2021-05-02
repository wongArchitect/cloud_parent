package com.mn.trans.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Controller
//@SpringBootApplication
//@EnableTransactionManagement
public class BootMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BootMain.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BootMain.class, args);
    }

}
