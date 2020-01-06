package com.hu.blogback.controller.admin;

import com.hu.blogback.pojo.Blog;
import com.hu.blogback.pojo.Comment;
import com.hu.blogback.pojo.User;
import com.hu.blogback.service.BlogService;
import com.hu.blogback.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        //return "redirect:/admin/comments";
        return "admin/comments";
    }

    @GetMapping("/view/{id}")
    @ResponseBody
    public Integer view(@PathVariable("id") Long id) {

        List<Comment> comments = commentService.listComentByBlogId(id);
        if (comments != null) {
            for (Comment comment :
                    comments) {
                if (!comment.isView()) {
                    comment.setView(true);
                    commentService.saveComment(comment);
                }

                // 修改其子级评论
                List<Comment> childs = comment.getComments();
                if (childs != null) {
                    for (Comment c :
                            childs) {
                        if (!c.isView()) {
                            c.setView(true);
                            commentService.saveComment(c);
                        }
                    }
                }
            }
            // 修改成功（暂时先这样吧，懒得改了）
            return 1;
        }
        //修改失败
        return 0;
    }

    @GetMapping("/ignore")
    @ResponseBody
    public Integer ignore() {

        List<Comment> comments = commentService.listCommentByIsView(false);
        if (comments == null) {
            return 0;//没有未读评论
        }
        for (Comment comment :
                comments) {
            comment.setView(true);
            commentService.saveComment(comment);
        }

        return 1;//操作成功
    }
}
