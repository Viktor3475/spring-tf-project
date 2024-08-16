package com.demo.spring_tf_project.service.implementations;

import com.demo.spring_tf_project.entity.User;
import com.demo.spring_tf_project.repository.UserRepository;
import com.demo.spring_tf_project.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

// Implementation of our service for a user entity
@Service
public class UserServiceImpl implements UserService {

    // Supplying the implementation with the methods of UserRepository dependency
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void save(User user) {
        userRepository.save(user); // save user entity in repository
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not user found by this id: " + id));
        // find user by id or throw an Exception: user not found by this id
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll(); // return list of all users
    }
    @Override
    public void deleteById(long id){
        userRepository.deleteById(id); // delete user by id
    }
}
