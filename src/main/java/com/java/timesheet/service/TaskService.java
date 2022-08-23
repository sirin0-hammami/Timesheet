package com.java.timesheet.service;

import com.java.timesheet.exception.ResourceNotFoundException;
import com.java.timesheet.model.Task;
import com.java.timesheet.model.User;
import com.java.timesheet.repository.ProjectRepository;
import com.java.timesheet.repository.TaskRepository;
import com.java.timesheet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Task> getAllTasks(){
        List<Task> tasksRecord = new ArrayList<>();
        taskRepository.findAll().forEach(tasksRecord::add);
        return tasksRecord;
    }

    public ResponseEntity<Task> getTaskById(Long id){
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + id));
        return ResponseEntity.ok().body(task);
    }
    public List<Task> getTaskByUser(Long iduser){
        User user = userRepository.findById(iduser).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + iduser));
        List<Task> tasks = taskRepository.findByUser(user);
        return tasks;
    }

    public Task addTask(Long iduser, Task task ){
        User user = userRepository.findById(iduser).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + iduser));
task.setUser(user);
        taskRepository.save(task);
        return task;
    }

    public ResponseEntity<Task> updateTask(Long idTask, Task newTaskDetails){
        Task task = taskRepository.findById(idTask).orElseThrow( () -> new ResourceNotFoundException("task not found"));
        task.setDescriptionTache(newTaskDetails.getDescriptionTache());
        task.setDateDepT(newTaskDetails.getDateDepT());
        task.setDateFinT(newTaskDetails.getDateFinT());
        task.setEtat(newTaskDetails.getEtat());
        task.setEstimation(newTaskDetails.getEstimation());
        Task updatedTask = taskRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    public void deleteTask(Long idTask){
        Task task = taskRepository.findById(idTask).orElseThrow(() -> new ResourceNotFoundException("task not found"));
        taskRepository.delete(task);
    }


}
