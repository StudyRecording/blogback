package com.hu.blogback.service;

import com.hu.blogback.pojo.User;

public interface UserService {

    User checkUser(String username, String password);
}
