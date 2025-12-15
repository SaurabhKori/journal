package com.saurabh.journal.controller;

import com.saurabh.journal.cache.AppCache;
import com.saurabh.journal.entity.User;
import com.saurabh.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private AppCache appCache;
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<User> users=userService.findAll();
        if(users.size()>0){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
    @GetMapping("clear_app_cache")
    public void clearAppCache(){
        appCache.init();
    }
}
