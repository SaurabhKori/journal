package com.saurabh.journal.cache;

import com.saurabh.journal.entity.ConfigJournalAppEntity;
import com.saurabh.journal.repository.ConfigJournalRepositry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class AppCache {
    public enum keys{
        WEATHER_API;
    }

    public Map<String,String> appCache;
    @Autowired
   private  ConfigJournalRepositry configJournalRepositry;
    @PostConstruct
    public void init(){
        appCache=new HashMap<>();
      List<ConfigJournalAppEntity> configJournalAppEntities= configJournalRepositry.findAll();
      for(ConfigJournalAppEntity configJournalAppEntity:configJournalAppEntities){
          appCache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
      }
    }
}

