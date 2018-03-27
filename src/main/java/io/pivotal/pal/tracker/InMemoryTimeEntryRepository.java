package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

public class InMemoryTimeEntryRepository {
    private Map<Long, TimeEntry> timeEntries = new HashMap<>();

    public InMemoryTimeEntryRepository() {
    }

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntries.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }

    public Object list() {
        return asList(timeEntries);
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        return null;
    }
}
