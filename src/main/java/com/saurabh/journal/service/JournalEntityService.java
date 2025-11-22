package com.saurabh.journal.service;

import com.saurabh.journal.entity.JouranlEntry;
import com.saurabh.journal.entity.User;
import com.saurabh.journal.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class JournalEntityService {
   @Autowired
    private JournalEntryRepository journalEntryRepository;
   @Autowired
    private UserService userService;
      public JouranlEntry saveJournalEntity(JouranlEntry jouranlEntry ,String username){
          User user=userService.findByUsername(username);
          jouranlEntry.setId(UUID.randomUUID().toString());
          jouranlEntry.setDate(LocalDateTime.now());
          JouranlEntry saved=journalEntryRepository.save(jouranlEntry);
           user.getJouranlEntries().add(saved);
           userService.save(user);
          return null;
      }
      public  List<JouranlEntry> findAllJournalEntry(){
       List<JouranlEntry> entrys= journalEntryRepository.findAll();
          return entrys;
      }
      public JouranlEntry findJournalEntryById(String id){
          return  journalEntryRepository.findById(id).get();
      }
      public JouranlEntry updateJournalEntryById(String id, JouranlEntry jouranlEntry){
          if(id!=null){
            boolean isExist=  journalEntryRepository.existsById(id);
            if(isExist){
                jouranlEntry.setId(id);
                jouranlEntry.setDate(LocalDateTime.now());
                return journalEntryRepository.save(jouranlEntry);
            }
          }
          return null;
      }

    public void deleteById(String id, String userName) {
          User user=userService.findByUsername(userName);
          user.getJouranlEntries().removeIf(jouranlEntry -> jouranlEntry.getId().equals(id));
          userService.save(user);
          journalEntryRepository.deleteById(id);
    }
}
