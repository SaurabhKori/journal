package com.saurabh.journal.service;

import com.saurabh.journal.entity.JouranlEntry;
import com.saurabh.journal.entity.User;
import com.saurabh.journal.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
      public Optional<JouranlEntry> findJournalEntryById(String id){
          return  journalEntryRepository.findById(id);
      }
      public JouranlEntry updateJournalEntryById(String id, JouranlEntry jouranlEntry){
          if(id!=null){
              Optional<JouranlEntry> oldjournalEntry=findJournalEntryById(id);
              if (oldjournalEntry.isPresent()) {
                jouranlEntry.setId(id);
                  jouranlEntry.setTitle(jouranlEntry.getTitle()!=null && !jouranlEntry.getTitle().equals("")?jouranlEntry.getTitle():oldjournalEntry.get().getTitle());

                  jouranlEntry.setContent(jouranlEntry.getContent()!=null && !jouranlEntry.getContent().equals("")?jouranlEntry.getContent():oldjournalEntry.get().getContent());
                jouranlEntry.setDate(LocalDateTime.now());
                return journalEntryRepository.save(jouranlEntry);
            }
          }
          return null;
      }
//   @Transactional
    public boolean deleteById(String id, String userName) {
       boolean removed=false;
          try {
              User user=userService.findByUsername(userName);
              removed= user.getJouranlEntries().removeIf(jouranlEntry -> jouranlEntry.getId().equals(id));
              if(removed){
                  userService.save(user);
                  journalEntryRepository.deleteById(id);
              }

          }catch(Exception e){
              e.printStackTrace();
              System.out.println(e.getMessage());
              throw new RuntimeException(e.getMessage());
          }

       return removed;
    }

}
