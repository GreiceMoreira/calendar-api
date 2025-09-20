package com.moreira.calendar.controller;

import org.springframework.web.bind.annotation.RestController;

import com.moreira.calendar.model.Calendar;
import com.moreira.calendar.model.Event;
import com.moreira.calendar.service.EventService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;


@RestController("/")
public class EventController {
    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    // Request to add a new event to the database
    @PostMapping(path = "events", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String newEvent(@RequestBody Event event) {
        return service.addEvent(event);
    }

    // Request to get all events from the database
    @GetMapping(path = "events", produces = MediaType.APPLICATION_JSON_VALUE)
    public Calendar getAllEvents() {
        return service.getAllEvents();
    }

    // Request to get one event by ID
    @GetMapping(value="events/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Event getEventById(@PathVariable("id") String id) {
        return service.getEventById(id);
    }

    // Request to get events by date
    @GetMapping(value="events/date/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getEventByDate(@PathVariable("date") LocalDate date) {
        return service.getEventByDate(date);
    }

    // Request to update an event by ID
    @PutMapping(path = "events/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateEvent(@PathVariable("id") String id, @RequestBody Event event) {
        service.updateEvent(event);
    }
    

    // Request to delete an event from the database by ID
    @DeleteMapping(path = "events/{id}")
    public void delete(@PathVariable("id") String id) {
        service.deleteEvent(id);
    }
    
}
