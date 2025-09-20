package com.moreira.calendar.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Calendar implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Event> events = new ArrayList<>();

    //Getter and setter for the list of events (Calendar)
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


    //Add an event to the list
    public void addEvent(Event event) {
        events.add(event);
    }

    //Remove an event from the list
    public void removeEvent(Event event) {
        events.remove(event);
    }

    //Update an event in the list
    public void updateEvent(Event event) {
        var index = getEventIndex(event);
        if (index < 0) {
            return;
        }
        events.set(index, event);
    }

    //Get the index of the event in the list
    private int getEventIndex(Event event) {
        return events.indexOf(event);
    }

    //Get an event from the list by its ID
    public Event getEventById(String id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }
}
