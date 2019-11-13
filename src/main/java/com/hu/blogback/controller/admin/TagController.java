package com.hu.blogback.controller.admin;

import com.hu.blogback.pojo.Tag;
import com.hu.blogback.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 4, sort = {"id"}, direction = Sort.Direction.ASC)
                                   Pageable pageable, Model model) {

        Page<Tag> page = tagService.listTag(pageable);
        //System.out.println("---------------: " + page.getContent().toString());
        model.addAttribute("page", page);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {

        Tag tag = tagService.getTag(id);
        model.addAttribute("tag", tag);
        return "admin/tags-input";
    }

    /**
     * 添加标签
     * @param tag
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {

        // 后端验证name不能为空
        if (result.hasErrors()) {
            return "admin/tags-input";
        }

        //后端验证分类名不能重复
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name", "nameError", "不能重复添加标签");
            return "admin/tags-input";
        }


        Tag t = tagService.saveTag(tag);
        if (t == null)  {
            attributes.addFlashAttribute("message", "操作失败！");
        }

        return "redirect:/admin/tags";
    }

    /**
     * 修改标签
     * @param tag
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,
                           Long id, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            return "admin/tags-input";
        }

        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name", "nameError", "不能重复添加标签");
            return "admin/tags-input";
        }


        Tag t = tagService.updateTag(id, tag);
        if (t == null)  {
            attributes.addFlashAttribute("message", "操作失败！");
        }

        return "redirect:/admin/tags";
    }

    /**
     * 删除标签
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {

        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }


}
