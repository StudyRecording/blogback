package com.hu.blogback.service;

import com.hu.blogback.pojo.BlogSetting;

import java.util.List;

public interface BlogSettingService {

    /**
     * 获取博客的相关设置
     * @return
     */
    Long getCount();

    /**
     * 获取博客的相关设置
     */
    List<BlogSetting> findBlogSetting();

    /**
     * 存储或修改博客设置
     */
    BlogSetting BlogSettingSaveOrUpdate(BlogSetting blogSetting);
}
