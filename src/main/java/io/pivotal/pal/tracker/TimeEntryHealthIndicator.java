package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Created by accenturelabs on 3/29/18.
 */
@Component
public class TimeEntryHealthIndicator implements HealthIndicator {
    private final TimeEntryRepository timeEntryRepo;
    private static final int MAX_TIME_ENTRIES = 5;

    public TimeEntryHealthIndicator(TimeEntryRepository timeEntryRepo){
        this.timeEntryRepo = timeEntryRepo;
    }

    @Override
    public Health health() {
        Health.Builder health = new Health.Builder();
        if (timeEntryRepo.list().size() < MAX_TIME_ENTRIES){
            health.up();
        } else {
            health.down();
        }
        return health.build();
    }
}
