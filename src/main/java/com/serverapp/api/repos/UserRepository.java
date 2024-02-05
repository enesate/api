package com.serverapp.api.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverapp.api.entites.User;


public interface UserRepository extends JpaRepository<User, Integer>{
    User findByuserName(String userName);
    


    Optional<User> findByUserNameAndPassword(String userName, String password);
}
