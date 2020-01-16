package com.hu.blogback.controller.admin;

import com.hu.blogback.pojo.BlogSetting;
import com.hu.blogback.service.BlogSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogSettingController {

    @Autowired
    private BlogSettingService blogSettingService;

    @GetMapping("/blogsetting")
    public String blogSetting() {
        return "admin/blog-setting";
    }

    /**
     * 还有部分未修改，代码不规范，但先 实现功能
     * @param blogSetting
     * @return
     */
    @PostMapping("/updatesetting")
    @ResponseBody
    public int updateSetting(BlogSetting blogSetting, HttpServletRequest request) {

        if (blogSetting.isSideClassification() || blogSetting.isSideQRcode()
            || blogSetting.isSideRecommend() || blogSetting.isSideTag()) {
            blogSetting.setSide(true);
        } else {
            blogSetting.setSide(false);
        }
        //System.out.println(blogSetting);
        BlogSetting blogSetting1 = blogSettingService.BlogSettingSaveOrUpdate(blogSetting);
        if (blogSetting1 != null) {
            request.getServletContext().setAttribute("setting", blogSetting1);
            return 1;
        } else {
            return 2;
        }
    }

    @GetMapping("/defaultsetting")
    @ResponseBody
    public int defaultSetting(HttpServletRequest request) {

        ServletContext servletContext = request.getServletContext();
        BlogSetting blogSetting = (BlogSetting) servletContext.getAttribute("setting");
        BlogSetting blogSetting1 = new BlogSetting();
        blogSetting1.setId(blogSetting.getId());
        servletContext.setAttribute("setting", blogSetting1);
        blogSettingService.BlogSettingSaveOrUpdate(blogSetting1);
        return 1;
    }

}
