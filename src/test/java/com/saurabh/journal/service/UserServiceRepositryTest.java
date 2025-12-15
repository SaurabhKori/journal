package com.saurabh.journal.service;

import com.saurabh.journal.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceRepositryTest {
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Test
    public void getUserList(){
        userRepositoryImpl.getUserForSA();
       System.out.println(userRepositoryImpl.getUserForSA());
    }
}
