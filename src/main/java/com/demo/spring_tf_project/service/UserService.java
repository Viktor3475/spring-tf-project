package com.demo.spring_tf_project.service;

import com.demo.spring_tf_project.entity.User;

import java.util.List;

public interface UserService { // this service is used for CRUD on user entity
    // we need only CRUD methods
    void save(User user);
    User findById(long id);
    List<User> findAll();
    void deleteById(long id);
}
