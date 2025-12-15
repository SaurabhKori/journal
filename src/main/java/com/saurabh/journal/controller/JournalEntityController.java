package com.saurabh.journal.controller;

import com.saurabh.journal.entity.JouranlEntry;
import com.saurabh.journal.entity.User;
import com.saurabh.journal.service.JournalEntityService;
import com.saurabh.journal.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journalEntry")
//@SecurityRequirement(name = "basicAuth")
public class JournalEntityController {
    @Autowired
  private JournalEntityService journalEntityService;
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<JouranlEntry> saveJournalEntry(@RequestBody JouranlEntry jouranlEntry) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            JouranlEntry jouranlEntry1=   journalEntityService.saveJournalEntity(jouranlEntry,username);

            return ResponseEntity.ok(jouranlEntry1);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping
    public ResponseEntity<List<JouranlEntry>> getAllJournalEntryofUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=userService.findByUsername(username);
        try {
            List<JouranlEntry> journal=    user.getJouranlEntries();
            return ResponseEntity.ok(journal);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/id/{id}")
    public ResponseEntity<JouranlEntry> getJournalEntryById(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=userService.findByUsername(username);
        List<JouranlEntry> journal=user.getJouranlEntries().stream().filter(data->data.getId().equals(id)).collect(Collectors.toList());
        if(journal.size()>0){
            ResponseEntity.ok(journalEntityService.findJournalEntryById(id));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND)  ;
    }
    @PutMapping("/id/{id}")
  public  ResponseEntity<?> updateJournalEntryById(@PathVariable String id,
                                                   @RequestBody JouranlEntry jouranlEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=userService.findByUsername(username);
        List<JouranlEntry> journal=user.getJouranlEntries().stream().filter(data->data.getId().equals(id)).collect(Collectors.toList());
        if(journal.size()>0){

                JouranlEntry  entry = journalEntityService.updateJournalEntryById(id, jouranlEntry);
                if(entry!=null){
                    return ResponseEntity.ok(entry);
                }

        }

        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  @DeleteMapping("id/{id}")
public ResponseEntity<?> deleteJournalEntryById(@PathVariable String id ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
     boolean removed= journalEntityService.deleteById(id,username);
     if (removed){
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }else{
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

}

}
