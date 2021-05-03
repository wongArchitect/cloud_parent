package com.mn.trans.controller;

import com.mn.trans.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/transfer")
    public String test() {
        try {
            // andy 给lucy转账50元
            accountService.transfer(1, 2, 50);
            return "转账成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "转账失败";
        }
    }

    @RequestMapping("/transferForResouces1")
    public Object transferForResouces1() {
        try {
            // andy 给lucy转账50元
            return accountService.prod();
        } catch (Exception e) {
            e.printStackTrace();
            return "test fail ...";
        }
    }

    @RequestMapping("/transferForResouces2")
    public Object transferForResouces2() {
        try {
            // andy 给lucy转账50元
            return accountService.dev();
        } catch (Exception e) {
            e.printStackTrace();
            return "test fail ...";
        }
    }
}
