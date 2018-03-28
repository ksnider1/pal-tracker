package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by accenturelabs on 3/27/18.
 */
@RestController
@RequestMapping("/time-entries")
public class TimeEntryController{
    private TimeEntryRepository timeEntriesRepository;

    public TimeEntryController(TimeEntryRepository repository){
        this.timeEntriesRepository = repository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry entry){
        TimeEntry createdTimeEntry = timeEntriesRepository.create(entry);
        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public  ResponseEntity read(@PathVariable long id){
        TimeEntry readEntry = timeEntriesRepository.find(id);
        if (readEntry == null){
            return new ResponseEntity(readEntry, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(readEntry, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list(){
        List<TimeEntry> entries = timeEntriesRepository.list();
        return new ResponseEntity<List<TimeEntry>>(entries, HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id,@RequestBody TimeEntry entry) {
        TimeEntry updatedEntry = timeEntriesRepository.update(id, entry);
        if (updatedEntry == null){
            return new ResponseEntity(updatedEntry, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedEntry, HttpStatus.OK);
    }

   @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        timeEntriesRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
