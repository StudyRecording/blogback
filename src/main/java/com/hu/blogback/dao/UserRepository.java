package com.hu.blogback.dao;

import com.hu.blogback.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsernameAndPasswordAndType(String username, String password, Integer type);

    int countByType(Integer type);
}
