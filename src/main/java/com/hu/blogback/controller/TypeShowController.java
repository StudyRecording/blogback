package com.hu.blogback.controller;

import com.hu.blogback.pojo.Type;
import com.hu.blogback.service.BlogService;
import com.hu.blogback.service.TypeService;
import com.hu.blogback.vo.BlogQuery;
import io.swagger.annotations.Api;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(tags = "文章相关分类接口")
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, @PathVariable Long id, Model model) {
        //List<Type> types = typeService.listTypeTop(300);
        List<Type> types = typeService.listTypeTopByPublished(50);
        model.addAttribute("types", types);
        if (id == -1) {
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
