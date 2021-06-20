package com.example.cashonline.service.impl;

import com.example.cashonline.dao.UserDao;
import com.example.cashonline.model.User;
import com.example.cashonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(Long id) {
        User user = userDao.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("The user you are looking for does not exist");
        }
        return user;
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userDao.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("The user you are trying to delete does not exist");
        }
        userDao.delete(user);

    }

    @Override
    public User saveUser(User user) {
        if (user == null) {
            throw new RuntimeException("The user you are trying to save does not exist");
        }
        return userDao.save(user);

    }
}
