package com.java.timesheet.service;
import java.util.*;

import com.java.timesheet.exception.ResourceNotFoundException;
import com.java.timesheet.model.Pointage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.java.timesheet.repository.UserRepository;
import com.java.timesheet.model.User;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private PointageService pointageService;


    public List<User> getAllUsers()
    {
        List<User>usersRecord = new ArrayList<>();
        userRepository.findAll().forEach(usersRecord::add);
        return usersRecord;
    }

    public List<User> getUserByNom(String nom)
    {
        List<User>usersRecord = new ArrayList<>();
        userRepository.findByNom(nom).forEach(usersRecord::add);
        return usersRecord;
    }

    public User getUserById( Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        return user;
    }

    public User getUserByEmail( String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public void addUser(User userModel)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPass =  passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encodedPass);
        userRepository.save(userModel);
    }

    public void deleteUser(Long id ){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        userRepository.delete(user);
    }

    public ResponseEntity<User> updateUser(Long id, User userDetails){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        user.setNom(userDetails.getNom());
        user.setPrenom(userDetails.getPrenom());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

}
