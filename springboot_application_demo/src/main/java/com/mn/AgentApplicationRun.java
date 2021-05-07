package com.mn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 11)
public class AgentApplicationRun implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {

    }
}
