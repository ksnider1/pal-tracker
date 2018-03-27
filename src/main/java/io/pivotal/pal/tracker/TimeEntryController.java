package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by accenturelabs on 3/27/18.
 */
public class TimeEntryController{
    public TimeEntryController(TimeEntryRepository repository){
    }
    
    public ResponseEntity create(TimeEntry entry){
        return null;
    }
    
    public  ResponseEntity read(long id){
        return null;
    }
    
    public ResponseEntity<List<TimeEntry>> list(){
        return null;
    }

    public ResponseEntity update(long l, TimeEntry expected) {
        return null;
    }

    public ResponseEntity<TimeEntry> delete(long l) {
        return null;
    }
}
