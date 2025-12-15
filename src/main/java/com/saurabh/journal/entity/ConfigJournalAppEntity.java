package com.saurabh.journal.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("config_journal_app")
@Data
public class ConfigJournalAppEntity {
    private ObjectId id;
    private String key;
    private String value;

}
