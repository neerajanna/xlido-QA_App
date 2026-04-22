package com.crio.xlido.repositories;

import java.util.HashMap;
import java.util.Map;
import com.crio.xlido.entities.Event;

public class EventRepository {

    private final Map<Long, Event> storage = new HashMap<>();

    public Event save(Event event) {
        storage.put(event.getId(), event);   // ✅ use entity ID
        return event;
    }

    public Event findById(long id) {
        return storage.get(id);
    }

    public boolean exists(long id) {
        return storage.containsKey(id);
    }

    public void delete(long id) {
        storage.remove(id);
    }
}
