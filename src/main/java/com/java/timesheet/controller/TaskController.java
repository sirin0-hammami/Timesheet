package com.java.timesheet.controller;

import com.java.timesheet.model.Task;
import com.java.timesheet.service.TaskService;
import com.java.timesheet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/tasks")
    public List<Task> getAllTasks(){
        List<Task> tasks = new ArrayList<>();
        taskService.getAllTasks().forEach(tasks::add);
        return tasks;
    }

    @RequestMapping(value="/tasks/user/{iduser}" , method = RequestMethod.GET)
    public List<Task> getTasksOfUser(@PathVariable("iduser") Long iduser){
        return taskService.getTaskByUser(iduser);
    }


    @RequestMapping(value="/task/{id}")
    public ResponseEntity<Task> getOneTask( @PathVariable(value = "id") Long id) {
        return taskService.getTaskById(id);
    }

    @RequestMapping(value="/addTask/{iduser}" , method = RequestMethod.POST)
    public Task addTask (@PathVariable("iduser") Long iduser , @RequestBody Task task){
        return taskService.addTask( iduser, task);
    }
    @RequestMapping(value="/updateTask/{idTask}" , method = RequestMethod.PUT)
    public ResponseEntity<Task>  updateTask(@PathVariable("idTask") Long idTask ,@RequestBody Task task){
        return taskService.updateTask(idTask, task);
    }
    @RequestMapping(value="/deleteTask/{idTask}")
    public void deleteTask(@PathVariable("idTask") Long idTask){
        taskService.deleteTask(idTask);
    }
}
