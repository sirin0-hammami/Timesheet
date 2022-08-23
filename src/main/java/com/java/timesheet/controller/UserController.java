package com.java.timesheet.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.java.timesheet.model.User;
import com.java.timesheet.service.UserService;

@RestController()
@RequestMapping()
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @RequestMapping("/usernom/{nom}")
    public List<User> getUserByNom(@PathVariable(value = "nom") String nom) {
        return userService.getUserByNom(nom);
    }


    @RequestMapping("/useremail/{email}")
    public User getUserByEmail(@PathVariable(value = "email") String email) {
        return userService.getUserByEmail(email);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getOneUser( @PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return("added");
    }
    @RequestMapping(value = "/delete-user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);

    }
    @RequestMapping(value = "/update-user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id , @RequestBody User userDetails){
        return userService.updateUser(id, userDetails);
    }

}
