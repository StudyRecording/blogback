package com.hu.blogback.dao;

import com.hu.blogback.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsernameAndPassword(String username, String password);

    int countByType(Integer type);
}
