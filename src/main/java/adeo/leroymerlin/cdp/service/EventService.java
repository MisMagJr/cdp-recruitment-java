package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getEvents();

    void deleteEvent(Long id);

    List<Event> getFilteredEvents(String query);
}
