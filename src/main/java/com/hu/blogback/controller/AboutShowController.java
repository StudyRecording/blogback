package com.hu.blogback.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Api("关于我页面相关接口")
@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about() {

        return "about";
    }
}
