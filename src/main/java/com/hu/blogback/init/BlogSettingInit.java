package com.hu.blogback.init;


import com.hu.blogback.pojo.Blog;
import com.hu.blogback.pojo.BlogSetting;
import com.hu.blogback.pojo.User;
import com.hu.blogback.service.BlogSettingService;
import com.hu.blogback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * 在应用启动时检查blog设置，如果第一次启动将配置 文件中的设置加载到数据库和ServletContext中
 */
@Component
public class BlogSettingInit /*implements ApplicationListener<ApplicationStartedEvent>*/ {

    @Autowired
    private BlogSettingService blogSettingService;

    @EventListener
    public void init(ApplicationStartedEvent applicationStartedEvent) {
        ServletContext servletContext = ((WebApplicationContext) applicationStartedEvent.getApplicationContext()).getServletContext();
        List<BlogSetting> blogSettings = blogSettingService.findBlogSetting();
        if (blogSettings.isEmpty() || blogSettings == null) {
            BlogSetting blogSetting = new BlogSetting();
            servletContext.setAttribute("setting", blogSetting);
            blogSettingService.BlogSettingSaveOrUpdate(blogSetting);
        }  else {
            servletContext.setAttribute("setting", blogSettings.get(0));
        }

    }
}
