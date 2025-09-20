package com.moreira.calendar.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

// import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import com.moreira.calendar.model.Calendar;
import com.moreira.calendar.model.Event;

//Methods that interact with the repository
@Repository
public class EventRepository {

    //Name of the file used to store the calendar
    private final String FILE_NAME = "myCalendar.txt";


    // Create an ID for the received event if it doesn't exist and save it to the database
    public Event save(Event event) {
        if (event.getId() == null) {
            event.setId(getNextId());
            saveNew(event);
        }
        else {
            update(event);
        }
        return event;
    }

    // Retrieve the calendar from the file and return the specific event by ID
    public Event findById(String id) {
        var calendar = getCalendarFromFile();
        return calendar.getEventById(id);
    }

    // Retrieve all events in the calendar
    public Calendar findAll() {
        return getCalendarFromFile();
    }

    // Retrieve the calendar, find the event by ID, and remove it from the data
    public void delete(String id) {
        var calendar = getCalendarFromFile();
        var event = calendar.getEventById(id);
        if (event == null) {
            return;
        }
        calendar.removeEvent(event);
        saveCalendarToFile(calendar);
    }

    // Retrieve the calendar, add a new event, and save the updated calendar to the file
    private void saveNew(Event event) {
        var calendar = getCalendarFromFile();
        calendar.addEvent(event);
        saveCalendarToFile(calendar);
    }

    // Retrieve the calendar, update the event, and save the updated calendar
    private void update(Event event) {
        var calendar = getCalendarFromFile();
        calendar.updateEvent(event);
        saveCalendarToFile(calendar);
    }   

    // Method to read the calendar from the file
    // If the file does not exist, initialize a new calendar
    // If it exists, return the calendar stored in the file
    private Calendar getCalendarFromFile() {
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) {
            return new Calendar();
        }

        Calendar calendar = null;
        try (FileInputStream file = new FileInputStream(FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(file)) {
            calendar = (Calendar) in.readObject();
            System.out.println("Calendar serialized and saved to " + FILE_NAME);
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return calendar;
    }
    
    // Save the calendar object to the filele
    private void saveCalendarToFile(Calendar calendar) {
        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(calendar);
            System.out.println("Calendar serialized and saved to " + FILE_NAME);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // Generate a random UUID as the ID for the event
    private String getNextId() {
        return UUID.randomUUID().toString();
    }

}
