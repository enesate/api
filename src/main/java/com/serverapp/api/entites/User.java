package com.serverapp.api.entites;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
    @Id
    @GeneratedValue
    Integer id;

    String userName;
    String password;

    String firstName;
    String lastName;
    String company;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Project> projects;
}
