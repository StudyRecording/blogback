package com.hu.blogback.service;

import com.hu.blogback.pojo.User;

public interface UserService {

    // 管理员
    final static int ADMIN = 0;

    // 普通用户
    final static int NORMAL = 1;

    /**
     * 获取账户信息
     * @param username
     * @param password
     * @param type 0：管理员账户, 1:普通用户
     * @return
     */
    User checkUser(String username, String password, Integer type);

    /**
     * 获取特定用户的数量
     * @param type 用户种类
     * @return
     */
    int getAdminCount(Integer type);

    /**
     * 注册一个用户
     * @param user
     * @return
     */
    User saveUser(User user);
}
