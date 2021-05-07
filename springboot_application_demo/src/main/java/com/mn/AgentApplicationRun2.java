package com.mn;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 10)
public class AgentApplicationRun2 implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }
}
