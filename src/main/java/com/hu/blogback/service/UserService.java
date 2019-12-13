package com.hu.blogback.service;

import com.hu.blogback.pojo.User;

public interface UserService {

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
     * 根据 Id获取用户信息
     */
    User getUser(Long id);

    /**
     * 注册或修改一个用户
     * @param user
     * @return
     */
    User saveUser(User user);
}
