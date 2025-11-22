package com.saurabh.journal.service;

import com.saurabh.journal.entity.User;
import com.saurabh.journal.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> findAll() {
       List<User> users= userRepository.findAll();
       return users;
    }
    public void save(User user) {
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
}
