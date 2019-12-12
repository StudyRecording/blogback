package com.hu.blogback.service;

import com.hu.blogback.dao.UserRepository;
import com.hu.blogback.pojo.User;
import com.hu.blogback.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password, Integer type) {

        User user = userRepository.findUserByUsernameAndPasswordAndType(username, MD5Util.code(password), type);
        return user;
    }

    @Override
    public int getAdminCount(Integer type) {
        return userRepository.countByType(type);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
