package com.hu.blogback.controller;

import com.hu.blogback.pojo.Tag;
import com.hu.blogback.service.BlogService;
import com.hu.blogback.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 4, sort = {"id"}, direction = Sort.Direction.DESC)
                                   Pageable pageable, @PathVariable Long id, Model model) {

        //List<Tag> tags = tagService.listTag();
        List<Tag> tags = tagService.listTagTopByPublishedCount(50);
        model.addAttribute("tags", tags);
        if(id == -1) {
            id = tags.get(0).getId();
        }
        System.out.println("--------------------:" + id);
        model.addAttribute("page", blogService.listBlog(id, pageable));
        model.addAttribute("activeTagId", id);
        return "/tags";
    }
}
