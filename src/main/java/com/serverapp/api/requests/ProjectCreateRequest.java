package com.serverapp.api.requests;

import lombok.Data;

@Data
public class ProjectCreateRequest {

    Integer id;
    String name;
    String description;
    Integer userId;
}