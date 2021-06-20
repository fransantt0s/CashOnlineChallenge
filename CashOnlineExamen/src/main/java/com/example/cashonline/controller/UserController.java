package com.example.cashonline.controller;

import com.example.cashonline.model.User;
import com.example.cashonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity <User> getUserById(@PathVariable("id") Long id){
        User user= userService.getUserById(id);
        return ResponseEntity.ok(user);

    }

    @PostMapping("/users")
    public ResponseEntity <User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return  ResponseEntity.ok(null);
    }









}
