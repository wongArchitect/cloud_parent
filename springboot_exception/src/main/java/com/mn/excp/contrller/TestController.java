package com.mn.excp.contrller;

import com.mn.excp.common.CodeMsg;
import com.mn.excp.common.GlobalException;
import com.mn.excp.dom.LoginVal;
import com.mn.excp.dom.ZingResult;
import com.mn.excp.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;
//    引入自定义注解+拦截器
//    通过注解标识需要被包装的类或方法。我们在请求进入时候拦截，为其打上注解标签，在返回时候进行标签验证，如果有才进行body重写，否则直接返回即可。


//   添加全局异常处理器后，controller方法是这样的：无业务逻辑
    //由于各种null，以及密码不正确等问题都在service抛出GlobalException，这里自然只能得到true
    //如果不是ResponseJson类型，则在ResponseJsonFormatHandler中转为ResponseJson类型，并可以代替controller中方法的返回类型，把ResponseJson写入ResponseBody并相应到前端。
    @GetMapping("/login")
//    @SneakyThrows //Lombok注解-@SneakyThrows,省去繁琐的try-catch.
    public ZingResult doLogin(@Valid LoginVal loginVal) {
        System.out.println("doLogin");
        log.info(loginVal.toString());
        CodeMsg loginCodeMsg = userService.login(loginVal);
        if (loginCodeMsg.getCode() != 0) {
            return ZingResult.error(GlobalException.SERVER_ERROR);
        }

        return ZingResult.success("成功");
    }
}
