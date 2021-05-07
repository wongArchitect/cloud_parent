package com.mn;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestDefaultFile {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(TestDefaultFile.class)
                //指定配置文件
                .properties("spring.config.location=classpath:/test-folder/my-config.properties")
                .run(args);
        // 输出变量
        System.out.println(context.getEnvironment().getProperty("jdbc.user"));
    }
}