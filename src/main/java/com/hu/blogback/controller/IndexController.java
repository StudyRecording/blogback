package com.hu.blogback.controller;

import com.hu.blogback.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {

//        int i = 4/0;
        Object obj = null;
        if (obj == null) {
            throw new NotFoundException("没有该博客!");
        }
        return "index";
    }

}
