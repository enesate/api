package com.serverapp.api.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.serverapp.api.entites.Block;
import com.serverapp.api.entites.Project;
import com.serverapp.api.entites.User;
import com.serverapp.api.repos.BlockRepository;
import com.serverapp.api.repos.ProjectRepository;
import com.serverapp.api.repos.UserRepository;
import com.serverapp.api.requests.ProjectUpdateRequest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ProjectService {
    @PersistenceContext
    private EntityManager entityManager;
    private ProjectRepository projectRepository;
    private BlockRepository blockRepository;
    private UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository,
            BlockRepository blockRepository, EntityManager entityManager) {
        this.projectRepository = projectRepository;
        this.blockRepository = blockRepository;
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    // tüm projeleri getiriyor
    public List<Project> getAllProject(String userName,String password) {
        Optional<User> user = userRepository.findByUserNameAndPassword(userName, password);
        if (user.isPresent()){
            User user1 = user.get(); 
            return projectRepository.findByUserId(user1.getId());
        }
        return null;
    }

    // sadece projeyi alıyoruz
    public Project getOneProjectById(Integer projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    // Bloklar ile birlikte projeyi alıyoruz
    public Project getProjectWithBlocks(Integer projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            // Projenin içindeki blokları çekiyoruz.
            List<Block> blocks = blockRepository.findByProject(project);
            project.setBlocks(blocks);

            // Bloklar arasındaki bağlantıları ekleyebilirsiniz (bu adımda bağlantıları
            // çekmenin mantığına bağlıdır).
            for (Block block : blocks) {
                List<Integer> connections = getConnectionsByBlockId(block.getId());
                block.setConnectedBlocks(connections);
            }

            return project;
        }

        return null;
    }

    public List<Integer> getConnectionsByBlockId(Integer blockId) {
        Optional<Block> optionalBlock = blockRepository.findById(blockId);

        if (optionalBlock.isPresent()) {
            Block block = optionalBlock.get();
            return block.getConnectedBlocks();
        }

        return Collections.emptyList();
    }

    public Project createOneProject(String userName, String password,Project project) {

        Optional<User> user = userRepository.findByUserNameAndPassword(userName, password);
        if (user.isPresent()) {
            User user1 = user.get();
            Project toSave = new Project();
            toSave.setId1(project.getId1());
            toSave.setName(project.getName());
            toSave.setDescription(project.getDescription());
            toSave.setUser(user1);
            toSave.setBlocks(null);
            return projectRepository.save(toSave);
        }
        return null;

    }

    public Project updateOneProjectById(Integer projectId, ProjectUpdateRequest updateProject) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isPresent()) {
            Project toUpdate = project.get();
            toUpdate.setName(updateProject.getName());
            toUpdate.setDescription(updateProject.getDescription());
            toUpdate.setBlocks(updateProject.getBlocks());
            projectRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public Project saveBlock(Integer projectId, List<Block> blocks) {
        Project project = clearBlocks1(projectId);
        if (project != null) {
            for (Block block : blocks) {
                block.setProject(project);
                blockRepository.save(block);
            }

            // Projeyi güncelle ve blokları getir
            // project.setBlocks(blockRepository.findByProject(project));
            return projectRepository.save(project);
        }

        return null;
    }

    public Project clearBlocks1(Integer projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            entityManager.clear();
            project.getBlocks().clear(); // Blok listesini temizle
            return projectRepository.save(project); // Projeyi güncelle
        }

        return null;
    }

    @Transactional
    public Project clearBlocks(Integer projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.setBlocks(null); // Blocks listesini temizle

            return projectRepository.save(project);
        }

        return null;
    }

}
