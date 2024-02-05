package com.serverapp.api.repos;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverapp.api.entites.Project;


public interface ProjectRepository extends JpaRepository <Project, Integer> {

    List<Project> findByUserId(Integer userId);
    
}
