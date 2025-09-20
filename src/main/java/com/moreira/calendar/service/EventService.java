package com.moreira.calendar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.moreira.calendar.model.Calendar;
import com.moreira.calendar.model.Event;
import com.moreira.calendar.repository.EventRepository;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public String addEvent(Event event) {
        validateEvent(event);

        event = repository.save(event);

        return event.getId();
    }

    public Event getEventById(String id) {
        var event = repository.findById(id);

        if (event == null) {
            throw new NoSuchElementException("No event found with id " + id);
        }

        return event;
    }

    public List<Event> getEventByDate(LocalDate date) {
        var calendar = repository.findAll();
        List<Event> eventsToday = new ArrayList<>();
        
        for (Event event : calendar.getEvents()) {
      
            if (event.getDate().isEqual(date)) {
                eventsToday.add(event);
            }            
        }
        return eventsToday;
    }

    public Calendar getAllEvents() {
        return repository.findAll();
    }

    public void updateEvent(Event event) {
        validateEvent(event);
        if (event.getId() == null) {
            throw new IllegalArgumentException( "id cannot be null");
        }
        repository.save(event);
    }

    public void deleteEvent(String id) {
        repository.delete(id);
    }

    private void validateEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException( "Event cannot be null");
        }
        if (event.getName() == null) {
            throw new IllegalArgumentException( "Name cannot be null");
        }
        if (event.getDate() == null) {
            throw new IllegalArgumentException( "Date cannot be null");
        }
        if (event.getType() == null) {
            throw new IllegalArgumentException( "Type cannot be null");
        }
    }

}
