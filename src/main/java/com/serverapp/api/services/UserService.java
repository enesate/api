package com.serverapp.api.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.serverapp.api.entites.User;
import com.serverapp.api.repos.UserRepository;

@Service
public class UserService {
     
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUser(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(String userName,String password, User newUser) {
        User user = userRepository.findByuserName(userName);
        if(user != null){
            User foundUser = user;
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            foundUser.setFirstName(newUser.getFirstName());
            foundUser.setLastName(newUser.getLastName());
            foundUser.setCompany(newUser.getCompany());
            userRepository.save(foundUser);
            return foundUser;
        }else
        //custom exception
        return null;
    }

    public void deleteById(Integer userId) {
        userRepository.deleteById(userId);
    }

    public ResponseEntity<User> signIn(String userName, String password) {
       User signedInUser  = signInWithUsernameAndPassword(userName, password);
        
        if (signedInUser != null) {
            return new ResponseEntity<>(signedInUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    public User signInWithUsernameAndPassword(String userName, String password) {
        User user = userRepository.findByuserName(userName);
        
        if (user != null && user.getPassword().equals(password)) {
            return user; // Kullanıcı adı ve şifre doğru
        } else {
            return null; // Kullanıcı adı veya şifre hatalı
        }
    }

    public User getOneUserByUserName(String userName) {
        return userRepository.findByuserName(userName);
    }

}
