package com.hu.blogback.dao;

import com.hu.blogback.pojo.BlogSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogSettingRepository extends JpaRepository<BlogSetting, Long> {
}
