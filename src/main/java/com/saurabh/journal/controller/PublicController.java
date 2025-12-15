package com.saurabh.journal.controller;

import com.saurabh.journal.entity.User;
import com.saurabh.journal.service.UserDetailServiceImpl;
import com.saurabh.journal.service.UserService;
import com.saurabh.journal.utilis.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @PostMapping("/signup")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }
    @PostMapping("/create-admin-user")
    public void createAdminUser(@RequestBody User user){
        userService.saveNewAdmin(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUsername());
          String jwt=  jwtUtil.generateToken(userDetails.getUsername());
          return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
//            throw new RuntimeException(e);
            log.error("Exception occurred while trying to login : {}", e.getMessage());
            return new  ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
        }


    }
}
