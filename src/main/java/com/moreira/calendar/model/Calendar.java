package com.moreira.calendar.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Calendar implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Event> events = new ArrayList<>();

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public void updateEvent(Event event) {
        var index = getEventIndex(event);
        if (index < 0) {
            return;
        }
        events.set(index, event);
    }

    private int getEventIndex(Event event) {
        return events.indexOf(event);
    }

    public Event getEventById(String id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }
}
