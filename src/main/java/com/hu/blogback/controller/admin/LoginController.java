package com.hu.blogback.controller.admin;

import com.hu.blogback.pojo.User;
import com.hu.blogback.service.UserService;
import com.hu.blogback.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {


        User user = userService.checkUser(username, password, UserService.ADMIN);

        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("error_message", "登录名或密码错误");
            return "redirect:/admin";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String pwd,
                           RedirectAttributes attributes) {

        if (username != null && !"".equals(username.trim())
                && email != null && !"".equals(email.trim())
                && pwd != null && !"".equals(pwd.trim())) {

            if (userService.getAdminCount(UserService.ADMIN) > 0) {
                attributes.addFlashAttribute("error_message", "已有管理员，不可再次注册管理员账户！");
                return "redirect:/admin";
            }

            User user = new User();
            user.setPassword(MD5Util.code(pwd));
            user.setAvatar("https://picsum.photos/100");
            user.setCreateTime(new Date());
            user.setEmail(email);
            user.setNickname(username);
            user.setType(UserService.ADMIN);
            user.setUpdateTime(new Date());
            user.setUsername(username);

            User user1 = userService.saveUser(user);

            if (user1 != null) {
                attributes.addFlashAttribute("success_message", "注册成功，请重新登录！");
            } else {
                attributes.addFlashAttribute("error_message", "注册失败！");
            }

        } else {
            attributes.addFlashAttribute("error_message", "注册失败！");
        }
        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
