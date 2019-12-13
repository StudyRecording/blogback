package com.hu.blogback.controller.admin;

import com.hu.blogback.pojo.User;
import com.hu.blogback.service.UserService;
import com.hu.blogback.util.FileUploadUtil;
import com.hu.blogback.util.MD5Util;
import com.hu.blogback.vo.AvatarUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class SettingController {

    @Value("${spring.saveFile.path}")
    private String rootPath;

    @Autowired
    private UserService userService;

    @GetMapping("/setting")
    public String setting() {

        return "admin/setting";
    }

    @GetMapping("/password")
    public String password() {
        return "admin/password";
    }

    @PostMapping("/setting")
    public String update(User user, HttpServletRequest request) {

        if (user == null || user.getId() == null) {
            request.setAttribute("err_message", "修改失败");
            return "admin/setting";
        }

        if (user.getNickname() == null || "".equals(user.getNickname()) ||
                user.getUsername() == null || "".equals(user.getUsername()) ||
                user.getEmail() == null || "".equals(user.getEmail())) {
            request.setAttribute("err_message", "修改失败, 昵称、用户名、邮箱不能为空！");
            return "admin/setting";
        }

        User user1 = userService.getUser(user.getId());
        user1.setUsername(user.getUsername());
        user1.setNickname(user.getNickname());
        user1.setEmail(user.getEmail());
        User user2 = userService.saveUser(user1);

        if (user2 == null) {
            request.setAttribute("err_message", "修改失败");
            return "admin/setting";
        }

        user2.setPassword(null);
        request.getSession().setAttribute("user", user2);
        request.setAttribute("success_message", "修改成功");
        return "admin/setting";
    }

    @PostMapping("/uploadImg")
    @ResponseBody
    public AvatarUrl updateAvatar(MultipartFile file, HttpServletRequest request) {

        String url = FileUploadUtil.imageUpload(file,
                rootPath,
                FileUploadUtil.IMAGE_PATH,
                request);

        System.out.println("--------------Url：" + url);

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return null;
        }

        User user1 = userService.getUser(user.getId());
        user1.setAvatar(url);
        if (userService.saveUser(user1) == null) {
            return null;
        }

        user1.setPassword(null);
        request.getSession().setAttribute("user", user1);

        // 之所以这么干就是懒得再加上json的依赖了
        AvatarUrl avatarUrl = new AvatarUrl();
        avatarUrl.setUrl(url);
        return avatarUrl;
    }

    @PostMapping("/password")
    public String password(String oldPassword,
                           String newPassword,
                           String confirmPassword,
                           HttpServletRequest request) {

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("err_message", "确认密码和新密码不匹配");
            return "admin/password";
        }

        if (oldPassword == null || "".equals(oldPassword.trim()) ||
            newPassword == null || "".equals(newPassword.trim()) ||
            confirmPassword == null || "".equals(confirmPassword.trim())) {

            request.setAttribute("err_message", "输入错误");
            return "admin/password";
        }

        User user = (User) request.getSession().getAttribute("user");
        User user1 = userService.getUser(user.getId());
        oldPassword = MD5Util.code(oldPassword);
        if (!user1.getPassword().equals(oldPassword)) {
            request.setAttribute("err_message", "密码输入错误");
            return "admin/password";
        }

        newPassword = MD5Util.code(newPassword);
        user1.setPassword(newPassword);
        userService.saveUser(user1);
        request.setAttribute("success_message", "密码修改成功");
        return "admin/password";
    }
}
