package com.serverapp.api.requests;

import java.util.List;

import com.serverapp.api.entites.Block;

import lombok.Data;

@Data
public class ProjectUpdateRequest {
    
    String name;
    String description;
    List<Block> blocks;

}
