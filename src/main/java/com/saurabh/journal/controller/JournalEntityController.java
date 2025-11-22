package com.saurabh.journal.controller;

import com.saurabh.journal.entity.JouranlEntry;
import com.saurabh.journal.entity.User;
import com.saurabh.journal.service.JournalEntityService;
import com.saurabh.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journalEntry")
public class JournalEntityController {
    @Autowired
  private JournalEntityService journalEntityService;
    @Autowired
    private UserService userService;
    @PostMapping("/{userName}")
    public ResponseEntity<JouranlEntry> saveJournalEntry(@RequestBody JouranlEntry jouranlEntry,@PathVariable String userName) {
        try{

            JouranlEntry jouranlEntry1=   journalEntityService.saveJournalEntity(jouranlEntry,userName);

            return ResponseEntity.ok(jouranlEntry1);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/{userName}")
    public ResponseEntity<List<JouranlEntry>> getAllJournalEntryofUser(@PathVariable String userName) {
        User user=userService.findByUsername(userName);
        try {
            List<JouranlEntry> journal=    user.getJouranlEntries();
            return ResponseEntity.ok(journal);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/id/{id}")
    public ResponseEntity<JouranlEntry> getJournalEntryById(@PathVariable String id) {
        return  ResponseEntity.ok(journalEntityService.findJournalEntryById(id));
    }
    @PutMapping("/id/{id}/{userName}")
  public  ResponseEntity<?> updateJournalEntryById(@PathVariable String id,
                                                   @RequestBody JouranlEntry jouranlEntry,
                                                   @PathVariable String userName
    ) {
        JouranlEntry  entry = journalEntityService.updateJournalEntryById(id, jouranlEntry);
        if(entry!=null){
            return ResponseEntity.ok(entry);
        }
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  @DeleteMapping("id/{id}/{userName}")
public ResponseEntity<?> deleteJournalEntryById(@PathVariable String id,@PathVariable String userName) {
     journalEntityService.deleteById(id,userName);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

}
