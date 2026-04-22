package com.crio.xlido.commands;

import java.util.List;
import com.crio.xlido.entities.Event;
import com.crio.xlido.services.EventService;

public class CreateEventCommand implements ICommand {

    private final EventService eventService;

    public CreateEventCommand(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public void invoke(List<String> tokens) {
        try {
            String eventName = tokens.get(1);
            long userId = Long.parseLong(tokens.get(2));

            Event event = eventService.createEvent(eventName, userId);

            // 🔥 THIS PRINT IS MANDATORY
            System.out.println("Event ID: " + event.getId());

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
