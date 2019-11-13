package com.hu.blogback.controller.admin;

import com.hu.blogback.pojo.Type;
import com.hu.blogback.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.ASC)
                                    Pageable pageable, Model model) {

        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {

        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {

        Type type = typeService.getType(id);
        model.addAttribute("type", type);
        return "admin/types-input";
    }

    /**
     * 添加分类
     * @param type
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {

        // 后端验证name不能为空
        if (result.hasErrors()) {
            return "admin/types-input";
        }

        //后端验证分类名不能重复
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name", "nameError", "不能重复添加分类");
            return "admin/types-input";
        }


        Type t = typeService.saveType(type);
        if (t == null)  {
            attributes.addFlashAttribute("message", "操作失败！");
        }

        return "redirect:/admin/types";
    }

    /**
     * 修改分类
     * @param type
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,
                           Long id, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            return "admin/types-input";
        }

        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name", "nameError", "不能重复添加分类");
            return "admin/types-input";
        }


        Type t = typeService.updateType(id, type);
        if (t == null)  {
            attributes.addFlashAttribute("message", "操作失败！");
        }

        return "redirect:/admin/types";
    }

    /**
     * 删除分类
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {

        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

}
