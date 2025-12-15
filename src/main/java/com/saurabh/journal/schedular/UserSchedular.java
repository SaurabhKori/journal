package com.saurabh.journal.schedular;

import com.saurabh.journal.entity.JouranlEntry;
import com.saurabh.journal.entity.User;
import com.saurabh.journal.enums.Sentiment;
import com.saurabh.journal.model.SentimentData;
import com.saurabh.journal.repository.UserRepositoryImpl;
import com.saurabh.journal.service.EmailService;
import com.saurabh.journal.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Component
public class UserSchedular {
    private final EmailService emailService;
    private final UserRepositoryImpl userRepository;
    private final SentimentAnalysisService sentimentAnalysisService;
    private final KafkaTemplate<String, SentimentData>  kafkaTemplate;
    public UserSchedular(
            EmailService emailService,
            UserRepositoryImpl userRepository,
            SentimentAnalysisService sentimentAnalysisService,
            KafkaTemplate<String, SentimentData>  kafkaTemplate
    ) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.sentimentAnalysisService = sentimentAnalysisService;
        this.kafkaTemplate = kafkaTemplate;
    }
//    @Scheduled(cron = "0 0 9 * * SUN")
//    @Scheduled(cron = "* * * ? * *")
    public void fetchUserAndSendSaMail(){
        List<User> users =userRepository.getUserForSA();
        for(User user:users){
            List<JouranlEntry> journalEntry = user.getJouranlEntries();
//            filter(x->x.getDate().isAfter(LocalDateTime.now().minus(1, ChronoUnit.DAYS)))
          List<Sentiment> filtered=  journalEntry.stream().map(map->map.getSentiment()).collect(Collectors.toList());
//
            Map<Sentiment,Integer> sentimentCounts=new HashMap<>();
            for(Sentiment sentiment:filtered){
                if(sentiment!=null){
                    sentimentCounts.put(sentiment,sentimentCounts.getOrDefault(sentiment,0)+1);
                }
            }
            Sentiment mostFrequentSentiment=null;
            int maxCount=0;
            for(Map.Entry<Sentiment,Integer> entry:sentimentCounts.entrySet()){
                if(entry.getValue()>maxCount){
                    maxCount=entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment!=null){
                          SentimentData sentimentData=SentimentData.builder().email(user.getEmail()).sentiment("Sentiment for last 7 days"+mostFrequentSentiment.toString()).build();
//                          emailService.sendEmail("c.anandamiraj@gmail.com","Sentiment for last 7 days",mostFrequentSentiment.toString());
//                          kafkaTemplate.send("weekly_sentiment",sentimentData.getEmail(),sentimentData);
                sentimentData.setEmail("c.anandamiraj@gmail.com");
                kafkaTemplate.send("weekly_sentiment",sentimentData.getEmail(),sentimentData);

            }
//          String seniment=sentimentAnalysisService.getSentimentAnalysis(join);
//          System.out.println("Hello Crons--->");

//          emailService.sendEmail("c.anandamiraj@gmail.com","Sentiment for last 7 days",seniment);
//            emailService.sendEmail("c.anandamiraj@gmail.com","Greetings and Looking Forward to Connecting","Dear Amit Raj Anand,\n" +
//                    "\n" +
//                    "I hope you are doing well.\n" +
//                    "I wanted to take a moment to greet you and express my appreciation for your time and support. It has always been a pleasure connecting with you, and I truly value your guidance and insights.\n" +
//                    "\n" +
//                    "Please feel free to share if there is anything I can assist you with. I am always available and happy to help.\n" +
//                    "\n" +
//                    "Looking forward to staying connected.\n" +
//                    "\n" +
//                    "Warm regards,\n" +
//                    "Saurabh Kori\n" +
//                    "Java Full Stack Developer");
        }
    }
}
