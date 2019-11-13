package com.hu.blogback.service;

import com.hu.blogback.pojo.Blog;
import com.hu.blogback.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

    /**
     * 获取博客文章信息
     * @param id
     * @return
     */
    Blog getBlog(Long id);

    /**
     * 获取博客信息列表
     * @param pageable 分页信息
     * @param blog 查询信息
     * @return
     */
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    /**
     * 获取博客列表信息
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Pageable pageable);

    /**
     * 获取前size条推荐的博客信息
     * @param size 推荐博客的数量
     * @return
     */
    List<Blog> listRecommendBlog(Integer size);

    /**
     * 添加博客文章
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 更新博客文章
     * @param id
     * @param blog
     * @return
     */
    Blog updateBlog(Long id, Blog blog);

    /**
     * 更新博客文章
     * @param id
     */
    void deleteBlog(Long id);
}
