package com.serverapp.api.requests;

import lombok.Data;

@Data
public class UserRegisterRequest {
    
    String userName;
    String password;
    String firstName;
    String lastName;
    String company;
}