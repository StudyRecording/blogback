package com.hu.blogback;

import com.hu.blogback.dao.CommentRepository;
import com.hu.blogback.pojo.Comment;
import com.hu.blogback.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommentTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void commentDaoTest() {
        List<Comment> byView = commentService.listCommentByIsView(false);
        for (Comment comment :
                byView) {
            System.out.println(comment);
        }
    }

}
