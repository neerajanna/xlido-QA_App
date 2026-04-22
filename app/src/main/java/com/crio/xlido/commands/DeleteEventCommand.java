package com.crio.xlido.commands;

import java.util.List;
import com.crio.xlido.services.EventService;

public class DeleteEventCommand implements ICommand {

    private final EventService eventService;

    public DeleteEventCommand(EventService eventService) {
        this.eventService = eventService;
    }

   
    @Override
public void invoke(List<String> tokens) {
    try {
        long eventId = Long.parseLong(tokens.get(1));
        long userId = Long.parseLong(tokens.get(2));
        eventService.deleteEvent(eventId, userId);
        System.out.println("EVENT_DELETED " + eventId);
    } catch (RuntimeException e) {
        System.out.println(e.getMessage());
    }
}

}
