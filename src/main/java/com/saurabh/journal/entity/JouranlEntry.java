package com.saurabh.journal.entity;

import com.saurabh.journal.enums.Sentiment;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "journalentry")
@Data
@Component
public class JouranlEntry {
    @Id
    private String id= UUID.randomUUID().toString();
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;
}
