package com.java.timesheet.service;
import java.util.*;

import com.java.timesheet.exception.ResourceNotFoundException;
import com.java.timesheet.model.Task;
import com.java.timesheet.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.java.timesheet.repository.ProjectRepository;
import com.java.timesheet.model.Project;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List<Project> getAllProjects()
    {
        List<Project>projectsRecord = new ArrayList<>();
        projectRepository.findAll().forEach(projectsRecord::add);
        return projectsRecord;
    }

    public List<Project> getProjectByNomProjet(String nom)
    {
        List<Project>projectsRecord = new ArrayList<>();
        projectRepository.findByNomProjet(nom).forEach(projectsRecord::add);
        return projectsRecord;
    }


    public Set<Task> getTaskByProject(Long idproject){
        Project projet =  projectRepository.findById(idproject).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + idproject));;
        Set<Task> tasks = projet.getTaches();
        return tasks;
    }


    public ResponseEntity<Project> getProjectById( Long id) {
        Project projet = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("project not exist with id :" + id));
        return ResponseEntity.ok().body(projet);
    }

    public void addProject(Project projet)
    {
        projectRepository.save(projet);
    }

    public void deleteProject(Long id ){
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("project not exist with id :" + id));
        projectRepository.delete(project);
    }

    public ResponseEntity<Project> updateProject(Long id, Project projectDetails){
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("project not exist with id :" + id));
        project.setNomProjet(projectDetails.getNomProjet());
        project.setDateDepP(projectDetails.getDateDepP());
        project.setDateFinP(projectDetails.getDateFinP());
        project.setStatut(projectDetails.getStatut());
        Project updatedProject = projectRepository.save(project);
        return ResponseEntity.ok(updatedProject);
    }

   public void taskToProject(Long idprojet, Long idtask){
        Project projet = projectRepository.findById(idprojet).orElseThrow(() -> new ResourceNotFoundException("project not found"));
        Task task = taskRepository.findById(idtask).orElseThrow(() -> new ResourceNotFoundException("task not found"));
        Set<Task> taches = projet.getTaches();
        taches.add(task);
        projet.setTaches(taches);
        projectRepository.save(projet);
    }
}
