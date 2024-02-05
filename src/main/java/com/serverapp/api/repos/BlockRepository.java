package com.serverapp.api.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverapp.api.entites.Block;
import com.serverapp.api.entites.Project;

public interface BlockRepository extends JpaRepository<Block, Integer>{

    List<Block> findByProject(Project project);
}
