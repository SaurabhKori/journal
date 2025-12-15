package com.saurabh.journal.controller;

import com.saurabh.journal.apiresponse.WeatherResponse;
import com.saurabh.journal.entity.User;
import com.saurabh.journal.service.UserService;
import com.saurabh.journal.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WeatherService weatherService;
    @GetMapping
    public User getAllUsers(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);
        return userInDb;
    }
    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);
        if(userInDb!=null){
            userInDb.setPassword(user.getPassword());
            userInDb.setUsername(user.getUsername());
            userService.saveNewUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
  public ResponseEntity<?> deleteUser(String username){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String user = authentication.getName();
    userService.deleteByUserName(user);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  @GetMapping("/greating")
  public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      WeatherResponse weatherResponse=weatherService.getWeather("Goa");
      String greeting="";
      if (weatherResponse!=null){
          greeting="Weather feels like"+weatherResponse.getCurrent();
      }
        return new ResponseEntity<>("Hi"+authentication.getName()+greeting,HttpStatus.OK);
  }
}
