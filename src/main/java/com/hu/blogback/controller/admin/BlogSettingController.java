package com.hu.blogback.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogSettingController {

    @GetMapping("/blogsetting")
    public String blogSetting() {
        return "admin/blog-setting";
    }
}
