package com.saurabh.journal.service;

import com.saurabh.journal.model.SentimentData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SentimentConsumerService {
    private EmailService emailService;
    @KafkaListener(topics = "weekly_sentiment",groupId = "weekly-sentiment-group")
    public void consume(SentimentData sentimentData){
        sendEmail(sentimentData);
    }
    private void sendEmail(SentimentData sentimentData){
        emailService.sendEmail(sentimentData.getEmail(),"Sentiment for last 7 days",sentimentData.getSentiment());

    }
}
