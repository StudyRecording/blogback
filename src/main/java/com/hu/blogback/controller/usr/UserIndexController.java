package com.hu.blogback.controller.usr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usr")
public class UserIndexController {

    @GetMapping("/index")
    public String index() {
        return "usr/index";
    }
}
