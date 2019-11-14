package com.hu.blogback.service;

import com.hu.blogback.pojo.Comment;

import java.util.List;

public interface CommentService {

    /**
     * 获取同一个博客的评论列表
     * @param id
     * @return
     */
    List<Comment> listComentByBlogId(Long id);

    /**
     * 存储评论信息
     * @param comment
     * @return
     */
    Comment saveComment(Comment comment);
}
