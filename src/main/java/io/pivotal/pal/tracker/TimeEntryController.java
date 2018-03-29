package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;

import java.util.List;

/**
 * Created by accenturelabs on 3/27/18.
 */
@RestController
@RequestMapping("/time-entries")
public class TimeEntryController{
    private final CounterService counter;
    private final GaugeService gauge;
    private TimeEntryRepository timeEntriesRepository;

    public TimeEntryController(TimeEntryRepository repository,
                               CounterService counter,
                               GaugeService gauge)
    {
        this.timeEntriesRepository = repository;
        this.counter = counter;
        this.gauge = gauge;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry entry){
        TimeEntry createdTimeEntry = timeEntriesRepository.create(entry);
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", timeEntriesRepository.list().size());

        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public  ResponseEntity read(@PathVariable long id){
        TimeEntry readEntry = timeEntriesRepository.find(id);
        if (readEntry == null){
            return new ResponseEntity(readEntry, HttpStatus.NOT_FOUND);
        }
        counter.increment("TimeEntry.read");

        return new ResponseEntity<>(readEntry, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list(){
        List<TimeEntry> entries = timeEntriesRepository.list();
        counter.increment("TimeEntry.listed");

        return new ResponseEntity<List<TimeEntry>>(entries, HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id,@RequestBody TimeEntry entry) {
        TimeEntry updatedEntry = timeEntriesRepository.update(id, entry);
        if (updatedEntry == null){
            return new ResponseEntity(updatedEntry, HttpStatus.NOT_FOUND);
        }
        counter.increment("TimeEntry.updated");

        return new ResponseEntity<>(updatedEntry, HttpStatus.OK);
    }

   @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        timeEntriesRepository.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", timeEntriesRepository.list().size());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
