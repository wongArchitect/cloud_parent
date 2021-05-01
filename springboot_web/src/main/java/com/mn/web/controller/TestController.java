package com.mn.web.controller;

import com.mn.web.anonation.inf.ResponseJsonFormat;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@ResponseJsonFormat
@RestController
@RequestMapping("/test")
public class TestController {

//    引入自定义注解+拦截器
//    通过注解标识需要被包装的类或方法。我们在请求进入时候拦截，为其打上注解标签，在返回时候进行标签验证，如果有才进行body重写，否则直接返回即可。
    @SneakyThrows //Lombok注解-@SneakyThrows,省去繁琐的try-catch.
    @GetMapping("/hello")
//    @ResponseJsonFormat
    public String sayHello() {
        log.info("进入方法...");
//        tess();
        return "what’s happening to you?";
    }

    public String tess() throws Exception {
        throw new Exception();
    }
}
