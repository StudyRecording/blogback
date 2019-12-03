package com.hu.blogback.service;

import com.hu.blogback.dao.BlogRepository;
import com.hu.blogback.exception.NotFoundException;
import com.hu.blogback.pojo.Blog;
import com.hu.blogback.pojo.Type;
import com.hu.blogback.util.MarkdownUtils;
import com.hu.blogback.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    /**
     * 博客的分页条件查询
     * @param pageable
     * @param blog
     * @return
     */
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {

        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(criteriaBuilder.like(
                            root.<String>get("title"),
                            '%' + blog.getTitle() + '%'));
                }
                if (blog.getTypeId() != null) {
                    predicates.add(criteriaBuilder.equal(
                            root.<Type>get("type").get("id"),
                            blog.getTypeId()));
                }
                if(blog.isRecommend()) {
                    predicates.add(criteriaBuilder.equal(
                            root.<Boolean>get("recommend"),
                            blog.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery('%' + query + '%', pageable);
    }

    @Override
    public Page<Blog> listBlog(Boolean published, Pageable pageable) {
        return blogRepository.findByQuery(published, pageable);
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {

        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Join join = root.join("tags");
                return criteriaBuilder.equal(join.get("id"), tagId);
            }
        }, pageable);
    }

    @Override
    public List<Blog> listRecommendBlog(Integer size) {

        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0,size,sort);
        return blogRepository.listRecommendBlog(pageable);
    }

    @Override
    public Blog getAndConvert(Long id) {

        Blog blog = blogRepository.getOne(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }

        blog.setViews(blog.getViews() + 1);
        updateBlog(blog.getId(), blog);
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return b;
    }

    /**
     * 新增博客
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {

        if (blog.getId() == null) { //新建blog
            blog.setCreateTime(new Date());
            blog.setViews(0);
            System.out.println("---------------------:" + blog);
        } else {//修改博客
            Blog b = getBlog(blog.getId());
            blog.setCreateTime(b.getCreateTime());
            blog.setViews(b.getViews());
            System.out.println("++++++++++++++++++++:" + blog);
        }
        blog.setUpdateTime(new Date());
        System.out.println("***********************:" + blog);
        //return blogRepository.save(blog);
        return  blogRepository.save(blog);
    }

    /**
     * 更新博客信息
     * @param id
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {

        Blog blog1 = blogRepository.getOne(id);
        if(blog1 == null) {
            throw new NotFoundException("库中未查找到该博客，无法执行修改操作！");
        }
        BeanUtils.copyProperties(blog, blog1);
        return blogRepository.save(blog1);
    }

    /**
     * 删除博客
     * @param id
     */
    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    @Override
    public Long countBlogByPublished() {
        List<Blog> blogs = blogRepository.findAll();
        long count = 0;
        for (Blog blog : blogs) {
            if (blog.isPublished()) {
                count++;
            }
        }
        return count;
    }
}
