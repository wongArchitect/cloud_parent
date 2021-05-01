package com.mn.web.controller;

import com.mn.web.common.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test1")
public class TestController1 {

//    @ApiOperation(value = "Swagger注释：包装体封装Json格式返回测试接口")
    @GetMapping("/hello")
    public ResponseJson sayHello() {
        log.info("进入方法...");
        String baby = "what’s happening to you?";
        return ResponseJson.ok(baby);
    }

//   Http请求获得响应返回进行包装,通过spring框架提供的ResponseBodyAdvice 和@ControllerAdvice可以用来实现。
//    注：这样所有的响应都将进行包装，这可能并不是我们想要的。
    @GetMapping("/hello2")
    public String sayHello2() {
        log.info("进入方法...");
        String baby = "what’s happening to you?";
        return baby;
    }

}
