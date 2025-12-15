package com.saurabh.journal.repository;

import com.saurabh.journal.entity.JouranlEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;


public interface JournalEntryRepository extends MongoRepository<JouranlEntry,String> {
}
