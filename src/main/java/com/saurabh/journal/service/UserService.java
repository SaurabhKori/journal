package com.saurabh.journal.service;

import com.saurabh.journal.entity.User;
import com.saurabh.journal.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private static final PasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public List<User> findAll() {
       List<User> users= userRepository.findAll();
       return users;
    }
    public void save(User user) {

        userRepository.save(user);
    }
    public void saveNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
    public void saveNewAdmin(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }
    public void delete(ObjectId id){
        userRepository.deleteById(id);
    }
    Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }
     public User findByUsername(String username) {
       return userRepository.findByUsername(username);
     }
    public void deleteByUserName(String username) {
        userRepository.deleteByUsername(username);
    }
}
