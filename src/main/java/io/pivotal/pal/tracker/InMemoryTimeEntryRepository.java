package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by accenturelabs on 3/27/18.
 */
public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private Map<Long, TimeEntry> timeEntries = new HashMap<>();
    private long sequencer = 1;


    public TimeEntry create(TimeEntry entry){
        entry.setId(sequencer);
        sequencer++;
        timeEntries.put(entry.getId(), entry);
        return entry;
    }

    public TimeEntry find(Long id){
        return timeEntries.get(id);
    }

    public List<TimeEntry> list(){
        List<TimeEntry> entries = new ArrayList<>();
        entries.addAll(timeEntries.values());

        return entries;
    }

    public TimeEntry update(Long id, TimeEntry entry){
        TimeEntry old = timeEntries.get(id);
        old.setDate(entry.getDate());
        old.setHours(entry.getHours());
        old.setProjectId(entry.getProjectId());
        old.setUserId(entry.getUserId());
        timeEntries.put(id, old);

        return old;
    }

    public void delete(Long id){
        timeEntries.remove(id);
    }
}
