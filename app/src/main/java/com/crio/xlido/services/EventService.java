package com.crio.xlido.services;

import com.crio.xlido.entities.Event;
import com.crio.xlido.repositories.EventRepository;
import com.crio.xlido.repositories.UserRepository;

public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Event createEvent(String name, long userId) {

        if (!userRepository.exists(userId)) {
            throw new RuntimeException(
                "ERROR: User with an id " + userId + " does not exist"
            );
        }

        Event event = new Event(name, userId);
        return eventRepository.save(event);
    }

    public void deleteEvent(long eventId, long userId) {

        //  FIX 1: check user first
        if (!userRepository.exists(userId)) {
            throw new RuntimeException(
                "ERROR: User with an id " + userId + " does not exist"
            );
        }

        //  FIX 2: then event
        Event event = eventRepository.findById(eventId);
        if (event == null) {
            throw new RuntimeException(
                "ERROR: Event with an id " + eventId + " does not exist"
            );
        }

        //  FIX 3: ownership
        if (event.getOrganizerId() != userId) {
            throw new RuntimeException(
                "ERROR: User with an id " + userId +
                " is not a organizer of Event with an id " + eventId
            );
        }

        eventRepository.delete(eventId);
    }
}
