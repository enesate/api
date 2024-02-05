package com.serverapp.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.serverapp.api.entites.Block;
import com.serverapp.api.entites.Project;
import com.serverapp.api.requests.ProjectUpdateRequest;
import com.serverapp.api.services.ProjectService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {

    ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{userName}/{password}")
    public List<Project> getAllProject(@PathVariable String userName,@PathVariable String password) {
        return projectService.getAllProject(userName,password);
    }

    @GetMapping("/{projectId}")
    public Project getOneProject(@PathVariable Integer projectId) {
        return projectService.getOneProjectById(projectId);
    }

    @GetMapping("/withBlocks/{projectId}")
    public Project getProjectWithBlocks(@PathVariable Integer projectId) {
        return projectService.getProjectWithBlocks(projectId);
    }

    @PostMapping("/{userName}/{password}")
    public Project createOneProject(@PathVariable String userName,@PathVariable String password,@RequestBody Project project){
        return projectService.createOneProject(userName,password,project);
    }

    @PostMapping("/{projectId}")
    public Project saveBlocks(@PathVariable Integer projectId, @RequestBody List<Block> blocks) {
        return projectService.saveBlock(projectId, blocks);
    }

    @DeleteMapping("/{projectId}/clear")
    public Project clearBlocks(@PathVariable Integer projectId) {
        return projectService.clearBlocks(projectId);
    }

    @PutMapping("/{projectId}")
    public Project updateOneProject(@PathVariable Integer projectId, @RequestBody ProjectUpdateRequest updateProject) {
        return projectService.updateOneProjectById(projectId, updateProject);
    }

}
