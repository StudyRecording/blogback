package com.hu.blogback.service;

import com.hu.blogback.dao.CommentRepository;
import com.hu.blogback.pojo.Blog;
import com.hu.blogback.pojo.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
//@CacheConfig(cacheNames = "footer_cache")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listComentByBlogId(Long id) {

        Sort sort = Sort.by(Sort.Direction.DESC,"createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(id,sort);
        return eachComment(comments);
    }

    @Override
    public Comment saveComment(Comment comment) {

        if (comment.getParentComment() != null) {

            Long parentCommentId = comment.getParentComment().getId();
            if(parentCommentId != -1) {
                comment.setParentComment(commentRepository.getOne(parentCommentId));
            } else {
                comment.setParentComment(null);
            }
        }

        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> listCommentByIsView(Boolean isView) {
        List<Comment> byView = commentRepository.findByViewOrderById(isView);
        return byView;
    }

    @Override
    public List<Blog> listBlogByComment(Boolean view) {
        Set<Blog> blogs = new HashSet<>();
        List<Comment> comments = listCommentByIsView(view);
        for (Comment comment : comments) {
            blogs.add(comment.getBlog());
        }
        return new ArrayList<>(blogs);
    }

    /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getComments();
            for(Comment reply1 : replys1)  {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 递归迭代
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getComments().size()>0) {
            List<Comment> replys = comment.getComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }
}
