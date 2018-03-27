package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

/**
 * Created by accenturelabs on 3/27/18.
 */
public class TimeEntry {


    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry() {

    }


    public long getId(){
        return this.id;
    }

    public TimeEntry(

            @Value("${ID:NOT SET}") long id,
            @Value("${PROJECT_ID:NOT SET}") long projectId,
            @Value("${USER_ID:NOT SET}") long userId,
            @Value("${DATE:NOT SET}") LocalDate date,
            @Value("${HOURS:NOT SET}") int hours

    ){

        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;

    }

    public TimeEntry(

            @Value("${ID:NOT SET}") long id,
            @Value("${PROJECT_ID:NOT SET}") long projectId,
            @Value("${DATE:NOT SET}") LocalDate date,
            @Value("${HOURS:NOT SET}") int hours

    ){

        this.id = id;
        this.projectId = projectId;
        this.date = date;
        this.hours = hours;

    }


}
