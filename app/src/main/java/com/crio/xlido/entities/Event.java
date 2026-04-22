package com.crio.xlido.entities;

public class Event {
    public static void resetCounter() {
        counter = 1;
    }
    private static long counter = 1;
    private final long id;
    private final String title;
    private final long organizerId;

    // Constructor used BEFORE saving (no id yet)
    public Event(String title, long organizerId) {
        this.id = counter++;
        this.title = title;
        this.organizerId = organizerId;
    }

    // Constructor used BY repository (assign id)

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getOrganizerId() {
        return organizerId;
    }

    @Override
    public String toString() {
        return "Event ID: " + id;
    }
}
