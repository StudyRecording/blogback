package com.hu.blogback.controller.admin;

import com.hu.blogback.pojo.Blog;
import com.hu.blogback.pojo.User;
import com.hu.blogback.service.BlogService;
import com.hu.blogback.service.TagService;
import com.hu.blogback.service.TypeService;
import com.hu.blogback.util.FileUploadUtil;
import com.hu.blogback.vo.ArticleImage;
import com.hu.blogback.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Value("${spring.saveFile.path}")
    private String realPath;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 4, sort = {"updateTime"}, direction = Sort.Direction.ASC)
                                Pageable pageable, BlogQuery blog, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 4, sort = {"updateTime"}, direction = Sort.Direction.ASC)
                                Pageable pageable, BlogQuery blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypesAndTags(model);
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    private void setTypesAndTags(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        setTypesAndTags(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {

        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTay(blog.getTagIds()));
        Blog b = blogService.saveBlog(blog);
        if (b == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }

        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {

        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", " 删除成功");
        return "redirect:/admin/blogs";
    }

    @PostMapping("/articleImage")
    @ResponseBody
    public ArticleImage articleImageUpload(
            @RequestParam(value = "editormd-image-file") MultipartFile file,
            HttpServletRequest request) {

        String url = FileUploadUtil.imageUpload(file,
                realPath,
                FileUploadUtil.ARTICLE_IMAGE_PATH,
                request);

        ArticleImage articleImage = new ArticleImage();
        if (url == null) {
            articleImage.setMessage("上传失败！");
            articleImage.setSuccess(0);
            articleImage.setUrl("");
        } else {
            articleImage.setMessage("上传成功!");
            articleImage.setSuccess(1);
            articleImage.setUrl(url);
        }

        return articleImage;

    }

}
