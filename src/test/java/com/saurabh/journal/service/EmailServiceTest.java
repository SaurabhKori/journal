package com.saurabh.journal.service;

import com.saurabh.journal.schedular.UserSchedular;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class EmailServiceTest {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserSchedular  userSchedular;
    @Test
    public void testSendEmail() {
        emailService.sendEmail("mrvicious27@gmail.com","Testing Java mail Sender","Hi aap Kaise Hai");
    }
    @Test
    public void testFetchUserAndSendMail(){
        userSchedular.fetchUserAndSendSaMail();
    }
}
