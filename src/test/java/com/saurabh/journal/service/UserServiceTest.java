package com.saurabh.journal.service;

import com.saurabh.journal.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class UserServiceTest {
    @Autowired
    public UserRepository userRepository;
    @Disabled
    @Test
    public  void testAddUser()
    {
      assertEquals(3,2+1);
    }
    @Disabled
    @Test
    public  void testFindUserName(){
        assertNotNull(userRepository.findByUsername("Ram"));
    }
    @Disabled
    @ParameterizedTest
    @CsvSource( {
            "Ram",
            "Shyam",
            "Gaurav",
    })
    public  void testFindUserNames(String userName){
        assertNotNull(userRepository.findByUsername(userName));
    }
}
