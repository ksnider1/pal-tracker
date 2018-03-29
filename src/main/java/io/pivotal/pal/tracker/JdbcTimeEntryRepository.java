package io.pivotal.pal.tracker;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * Created by accenturelabs on 3/29/18.
 */
public class JdbcTimeEntryRepository implements TimeEntryRepository {
    private JdbcTemplate template;
    private Map<Long, TimeEntry> entries = new HashMap<>();
    private long sequencer = 1;

    public JdbcTimeEntryRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public TimeEntry create(TimeEntry any) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        template.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO time_entries (project_id, user_id, date, hours) " +
                            "VALUES (?, ?, ?, ?)",
                    RETURN_GENERATED_KEYS
            );

            statement.setLong(1, any.getProjectId());
            statement.setLong(2, any.getUserId());
            statement.setDate(3, Date.valueOf(any.getDate()));
            statement.setInt(4, any.getHours());

            return statement;
        }, generatedKeyHolder);

        return find(generatedKeyHolder.getKey().longValue());
    }

    @Override
    public TimeEntry find(long id) {
        String sql = "SELECT * FROM time_entries WHERE id = ?";
        SqlRowSet results = template.queryForRowSet(sql, id);
        if (results.next()){
            TimeEntry entry = mapRowToEntry(results);
            return entry;
        }
        return null;
    }

    private TimeEntry mapRowToEntry(SqlRowSet results) {
        TimeEntry entry = new TimeEntry();
        entry.setId(results.getLong("id"));
        entry.setUserId(results.getLong("user_id"));
        entry.setProjectId(results.getLong("project_id"));
        entry.setDate(results.getDate("date").toLocalDate());
        entry.setHours(results.getInt("hours"));
        return entry;
    }

    @Override
    public List<TimeEntry> list() {
        String sql = "SELECT * FROM time_entries";
        SqlRowSet results = template.queryForRowSet(sql);
        List<TimeEntry> entries = new ArrayList<>();
        while (results.next()) {
            entries.add(mapRowToEntry(results));
        }
        return entries;
    }
    @Override
    public TimeEntry update(long id, TimeEntry expected) {
        String sql = "UPDATE time_entries SET user_id = ?, project_id = ?, date = ?, hours = ? WHERE id = ?";
        template.update(sql, expected.getUserId(), expected.getProjectId(), expected.getDate(), expected.getHours(), id);

        return find(id);
    }

    @Override
    public void delete(long l) {
        String sql = "DELETE FROM time_entries WHERE id = ?";
        template.update(sql, l);
    }
}
