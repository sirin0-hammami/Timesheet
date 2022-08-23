package com.java.timesheet.controller;
import java.util.List;
import java.util.Set;

import com.java.timesheet.model.Task;
import com.java.timesheet.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.java.timesheet.model.Project;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/projects")
    public List<Project> getAllProject() {
        return projectService.getAllProjects();
    }

    @RequestMapping("/projetnom/{nom}")
    public List<Project> getProjectByNom(@PathVariable(value = "nom") String nom) {
        return projectService.getProjectByNomProjet(nom);
    }

    @RequestMapping(value = "/projet/{id}", method = RequestMethod.GET)
    public ResponseEntity<Project> getOneProject(@PathVariable(value = "id") Long id) {
        return projectService.getProjectById(id);
    }

    @RequestMapping(value="/tasks/projectx/{idproject}" , method = RequestMethod.GET)
    public Set<Task> getTasksOfProject(@PathVariable("idproject") Long idproject){
        return projectService.getTaskByProject(idproject);
    }

    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public String addProject(@RequestBody Project project) {
        projectService.addProject(project);
        return ("added");
    }

    @RequestMapping(value = "/task/{idtask}/listOfProjects/{idprojet}" , method = RequestMethod.PUT)
    public void addTaskToProject(@PathVariable("idtask") Long idtask , @PathVariable("idprojet") Long idprojet){
        projectService.taskToProject(idprojet, idtask);
    }

    @RequestMapping(value = "/delete-project/{id}", method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable(value = "id") Long id) {
        projectService.deleteProject(id);

    }
    @RequestMapping(value = "/update-project/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Project> updateProject(@PathVariable(value = "id") Long id , @RequestBody Project projectDetails){
        return projectService.updateProject(id, projectDetails);
    }
}
