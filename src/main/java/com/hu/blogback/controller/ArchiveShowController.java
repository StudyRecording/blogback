package com.hu.blogback.controller;

import com.hu.blogback.service.BlogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "文章归档的接口")
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {

        model.addAttribute("archiveMap", blogService.archiveBlog());
//        model.addAttribute("blogCount", blogService.countBlog());
        model.addAttribute("blogCount", blogService.countBlogByPublished());
        return "/archives";
    }
}
