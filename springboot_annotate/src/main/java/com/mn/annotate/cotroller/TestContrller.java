package com.mn.annotate.cotroller;

import com.mn.annotate.token.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@RestController
@Controller
public class TestContrller {
    /**
     * 使用了@Token，如果在切面类中没有经过校验验证，会直接抛出异常，checkToken方法中不会进入
     */
    @Token
    @ResponseBody()
//    @RequestMapping(value = "checkToken", method = RequestMethod.POST)
    public String checkToken(@RequestBody Map<String,Object> token) {
        // 自己的业务实现
        return null;
    }

}
