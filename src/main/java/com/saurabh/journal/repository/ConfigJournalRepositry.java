package com.saurabh.journal.repository;

import com.saurabh.journal.entity.ConfigJournalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalRepositry extends MongoRepository<ConfigJournalAppEntity, ObjectId> {
}
