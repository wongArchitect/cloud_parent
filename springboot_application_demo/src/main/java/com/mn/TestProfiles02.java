package com.mn;

import org.apache.juli.WebappProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class TestProfiles02 {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(TestProfiles02.class)
                .properties("spring.config.location=classpath:/test-profiles.yml")
                //是否是web环境
                .web(false)
                //指定环境
                .profiles("oracle")
                .run(args);
        // 输出变量
        System.out.println(context.getEnvironment().getProperty("jdbc.driver"));
    }
}