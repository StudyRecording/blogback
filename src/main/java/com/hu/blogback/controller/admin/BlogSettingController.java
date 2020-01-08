package com.hu.blogback.controller.admin;

import com.hu.blogback.pojo.BlogSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class BlogSettingController {

    @Autowired
    private BlogSetting blogSetting;

    @GetMapping("/blogsetting")
    public String blogSetting() {
        return "admin/blog-setting";
    }

    @GetMapping("/setting-show")
    @ResponseBody
    public BlogSetting settingShow() {
        return this.blogSetting;
    }
}
