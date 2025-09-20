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

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import com.moreira.calendar.model.Calendar;
import com.moreira.calendar.model.Event;

@Repository
public class EventRepository {

    private final String FILE_NAME = "myCalendar.txt";

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

    public Event findById(String id) {
        var calendar = getCalendarFromFile();
        return calendar.getEventById(id);
    }

    public Calendar findAll() {
        return getCalendarFromFile();
    }

    public void delete(String id) {
        var calendar = getCalendarFromFile();
        var event = calendar.getEventById(id);
        if (event == null) {
            return;
        }
        calendar.removeEvent(event);
        saveCalendarToFile(calendar);
    }

    private void saveNew(Event event) {
        var calendar = getCalendarFromFile();
        calendar.addEvent(event);
        saveCalendarToFile(calendar);
    }

    private void update(Event event) {
        var calendar = getCalendarFromFile();
        calendar.updateEvent(event);
        saveCalendarToFile(calendar);
    }

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return calendar;
    }
    
    private void saveCalendarToFile(Calendar calendar) {
        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(calendar);
            System.out.println("Calendar serialized and saved to " + FILE_NAME);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private String getNextId() {
        return UUID.randomUUID().toString();
    }

}
