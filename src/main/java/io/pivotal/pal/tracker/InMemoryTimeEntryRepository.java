package io.pivotal.pal.tracker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private HashMap<Long, TimeEntry> repo = new HashMap<>();
    private long seq = 0;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry newEntry = new TimeEntry();
        seq = seq + 1;
        newEntry.setId(seq);
        newEntry.setUserId(timeEntry.getUserId());
        newEntry.setProjectId(timeEntry.getProjectId());
        newEntry.setDate(timeEntry.getDate());
        newEntry.setHours(timeEntry.getHours());
        repo.put(seq, newEntry);
        timeEntry.setId(seq);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return repo.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList<TimeEntry>();
        repo.forEach((key, value) -> list.add(value));
        return list;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(repo.containsKey(id)){
            TimeEntry newEntry = new TimeEntry();
            newEntry.setId(id);
            newEntry.setProjectId(timeEntry.getProjectId());
            newEntry.setUserId(timeEntry.getUserId());
            newEntry.setDate(timeEntry.getDate());
            newEntry.setHours(timeEntry.getHours());
            repo.put(id, newEntry);
            return repo.get(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        repo.remove(id);
    }
}
