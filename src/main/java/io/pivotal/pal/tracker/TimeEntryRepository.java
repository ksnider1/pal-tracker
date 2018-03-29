package io.pivotal.pal.tracker;

import java.util.List;

/**
 * Created by accenturelabs on 3/27/18.
 */
public interface TimeEntryRepository {
    TimeEntry create(TimeEntry entry);
    TimeEntry find(long id);
    List<TimeEntry> list();
    TimeEntry update(long l, TimeEntry entry);
    void delete(long l);
}
