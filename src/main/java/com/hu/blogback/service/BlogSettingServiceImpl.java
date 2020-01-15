package com.hu.blogback.service;

import com.hu.blogback.dao.BlogSettingRepository;
import com.hu.blogback.pojo.BlogSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogSettingServiceImpl implements BlogSettingService {

    @Autowired
    private BlogSettingRepository blogSettingRepository;

    @Override
    public Long getCount() {
        return blogSettingRepository.count();
    }

    @Override
    public List<BlogSetting> findBlogSetting() {
        return blogSettingRepository.findAll();
    }

    @Override
    public BlogSetting BlogSettingSaveOrUpdate(BlogSetting blogSetting) {
        return blogSettingRepository.save(blogSetting);
    }
}
