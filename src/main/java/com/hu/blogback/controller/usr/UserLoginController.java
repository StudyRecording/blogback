package com.hu.blogback.controller.usr;

import com.hu.blogback.pojo.User;
import com.hu.blogback.service.UserService;
import com.hu.blogback.util.MD5Util;
import com.hu.blogback.util.NonsenseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/usr")
public class UserLoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String login() {
        return "usr/login";
    }

    @PostMapping("/reg")
    public String sign(User user, RedirectAttributes attributes) {
        if (user != null) {
            user.setNickname(user.getUsername());
            user.setAvatar("https://picsum.photos/100");
            user.setPassword(MD5Util.code(user.getPassword()));
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            User user1 = userService.saveUser(user);
            if (user1 != null) {
                attributes.addFlashAttribute("success_message", "注册成功");
                return "redirect:/usr";
            }
        }
        attributes.addFlashAttribute("error_message", "注册失败");
        return "redirect:/usr";
    }

    @PostMapping("/login")
    public String login(User user, HttpSession session, RedirectAttributes attributes)  {

        User user1 = userService.checkUser(user.getUsername(), user.getPassword(), user.getType());
        if (user1 != null) {
            user1.setPassword("");
            session.setAttribute("usr", user1);
            return "usr/index";
        }
        attributes.addFlashAttribute("error_message", "登录失败");
        return "redirect:/usr";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("usr");
        return "redirect:/";
    }
}
