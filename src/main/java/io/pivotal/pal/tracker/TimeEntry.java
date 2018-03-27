package io.pivotal.pal.tracker;


import java.time.LocalDate;

public class TimeEntry {
    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry(long id, long projectId, LocalDate date, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.date = date;
        this.hours = hours;
    }

    public long getId() {
        return id;
    }
}




//repo.create(new TimeEntry(123L, 456L, LocalDate.parse("2017-01-08"), 8));