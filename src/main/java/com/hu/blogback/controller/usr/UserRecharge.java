package com.hu.blogback.controller.usr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/usr")
public class UserRecharge {

    @GetMapping("/recharge")
    public String recharge() {
        return "usr/recharge";
    }

    @GetMapping("/alipay")
    @ResponseBody
    public void alipay() {
        System.out.println("------------支付Controller！");
    }
}
