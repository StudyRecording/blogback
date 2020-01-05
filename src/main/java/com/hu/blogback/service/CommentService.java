package com.hu.blogback.service;

import com.hu.blogback.pojo.Blog;
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

    /**
     * 根据isView的状态查找所有博客评论
     * @param isView
     * @return
     */
    List<Comment> listCommentByIsView(Boolean isView);

    /**
     * 根据评论信息是否已读获取博客列表
     * @return
     */
    List<Blog> listBlogByComment(Boolean view);
}
