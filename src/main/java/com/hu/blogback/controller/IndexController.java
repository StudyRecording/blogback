package com.hu.blogback.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/{id}/{name}")
    public String index(@PathVariable Integer id, @PathVariable String name) {

//        System.out.println("id: " + id + " name: " + name);
//        int i = 4/0;
        return "index";
    }

}
