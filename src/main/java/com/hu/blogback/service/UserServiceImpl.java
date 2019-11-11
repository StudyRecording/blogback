package com.hu.blogback.service;

import com.hu.blogback.dao.UserRepository;
import com.hu.blogback.pojo.User;
import com.hu.blogback.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {

        User user = userRepository.findUserByUsernameAndPassword(username, MD5Util.code(password));
        return user;
    }
}
