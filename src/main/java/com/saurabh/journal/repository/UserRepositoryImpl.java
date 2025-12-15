package com.saurabh.journal.repository;

import com.saurabh.journal.entity.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserRepositoryImpl {
    @Autowired
    MongoTemplate mongoTemplate;
    public List<User> getUserForSA() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("email").exists(true));
//        query.addCriteria(Criteria.where("email").ne(null).ne(""));
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
     return mongoTemplate.find(query, User.class);
    }
}
