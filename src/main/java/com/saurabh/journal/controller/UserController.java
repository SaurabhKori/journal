package com.saurabh.journal.controller;

import com.saurabh.journal.entity.User;
import com.saurabh.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }
    @PostMapping
    public void createUser(@RequestBody User user){
        userService.save(user);
    }
    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDb = userService.findByUsername(userName);
        if(userInDb!=null){
            userInDb.setPassword(user.getPassword());
            userInDb.setUsername(user.getUsername());
            userService.save(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
