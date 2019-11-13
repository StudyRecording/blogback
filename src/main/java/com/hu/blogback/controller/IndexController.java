package com.hu.blogback.controller;

import com.hu.blogback.pojo.Blog;
import com.hu.blogback.service.BlogService;
import com.hu.blogback.service.TagService;
import com.hu.blogback.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 6, sort = {"createTime"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTag(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlog(8));
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {

        return "blog";
    }


}
