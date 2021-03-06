package com.hu.blogback.service;

import com.hu.blogback.pojo.Blog;
import com.hu.blogback.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

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
     * 根据条件进行分页查询
     * @param query 文章标题或内容包含的字符串
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(String query, Pageable pageable);

    /**
     * 获取草稿博客列表或者发布博客列表
     * @param published true：发布状态，false：草稿状态
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Boolean published, Pageable pageable);
    /**
     * 获取标签id的所有博客文章
     * @param tagId
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Long tagId, Pageable pageable);

    /**
     * 获取前size条推荐的博客信息
     * @param size 推荐博客的数量
     * @return
     */
    List<Blog> listRecommendBlog(Integer size);

    /**
     * 获取content为html文本的Blog
     * @param id
     * @return
     */
    Blog getAndConvert(Long id);

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

    /**
     * 以年份分类获取博客
     * @return
     */
    Map<String, List<Blog>> archiveBlog();

    /**
     * 获取博客的条数
     * @return
     */
    Long countBlog();

    /**
     * 获取已发布的博客数
     */
    Long countBlogByPublished();
}
