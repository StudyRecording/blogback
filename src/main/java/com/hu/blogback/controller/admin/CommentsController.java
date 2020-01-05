package com.hu.blogback.controller.admin;

import com.hu.blogback.pojo.Blog;
import com.hu.blogback.pojo.Comment;
import com.hu.blogback.pojo.User;
import com.hu.blogback.service.BlogService;
import com.hu.blogback.service.CommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/comments")
    public String comment(Model model) {

        List<Blog> blogs = commentService.listBlogByComment(false);
        for (Blog blog :
                blogs) {
            List<Comment> comments = commentService.listComentByBlogId(blog.getId());
            blog.setComments(comments);
        }
        model.addAttribute("blogs", blogs);
        return "admin/comments";
    }

    @PostMapping("/comments")
    public String postComments(Comment comment, HttpSession session) {

        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User) session.getAttribute("user");
        comment.setAvatar(user.getAvatar());
        comment.setNickname(user.getNickname());
        comment.setEmail(user.getEmail());
        comment.setAdminComment(true);
        comment.setView(true);
        commentService.saveComment(comment);

        return "redirect:/admin/comments";
    }


}
