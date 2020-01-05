package com.hu.blogback.controller;

import com.hu.blogback.pojo.Blog;
import com.hu.blogback.pojo.Comment;
import com.hu.blogback.service.BlogService;
import com.hu.blogback.service.CommentService;
import com.hu.blogback.service.TagService;
import com.hu.blogback.service.TypeService;
import com.hu.blogback.util.NonsenseUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 6, sort = {"createTime"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model) {

        Boolean isPublished = NonsenseUtil.ArticlePublish.PUBLISH.isPublished();
        model.addAttribute("page", blogService.listBlog(isPublished, pageable));
        //model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("types", typeService.listTypeTopByPublished(6));
        //model.addAttribute("tags", tagService.listTag(10));
        model.addAttribute("tags", tagService.listTagTopByPublishedCount(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlog(8));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {

//        List<Comment> comments = commentService.listComentByBlogId(id);
        model.addAttribute("comments", commentService.listComentByBlogId(id));
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 6, sort = {"createTime"}, direction = Sort.Direction.DESC)
                                 @RequestParam String query, Pageable pageable, Model model) {

        model.addAttribute("page", blogService.listBlog(query, pageable));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/footer/newBlogs")
    public String footerBlog(Model model) {

        model.addAttribute("newBlogs", blogService.listRecommendBlog(3));
        return "_fragment :: footerBlog";
    }


}
