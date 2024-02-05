
package com.serverapp.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.serverapp.api.services.UserService;

import com.serverapp.api.entites.User;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //Burda /users post metodunu kullandığımızda kayıt işlemi yapıyoruz
    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Integer userId) {
        //custom exception ekle
        return userService.getOneUser(userId);
    }
    
    @PutMapping("/{userName}")
    public User updateOneUser(@PathVariable String userName,@RequestBody User newUser) {
        return userService.updateOneUser(userName,newUser.getPassword(),newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> signInWithUsernameAndPassword( @RequestParam String userName,
            @RequestParam String password) {
            return userService.signIn(userName,password);
    }
    

    


    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Integer userId){
        userService.deleteById(userId);
    }

}