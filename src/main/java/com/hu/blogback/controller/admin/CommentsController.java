package com.hu.blogback.controller.admin;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CommentsController {

    @GetMapping("/comments")
    public String comment() {
        return "admin/comments";
    }


}
