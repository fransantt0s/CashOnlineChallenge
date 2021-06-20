package com.example.cashonline.service;

import com.example.cashonline.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User getUserById(Long id);
    void deleteUserById(Long id);
    User saveUser(User user);
}
