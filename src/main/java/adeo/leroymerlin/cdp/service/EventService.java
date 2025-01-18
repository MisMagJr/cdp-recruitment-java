package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.business.EventBO;

import java.util.List;

public interface EventService {
    List<EventBO> getEvents();

    void deleteEvent(Long id);

    List<EventBO> getFilteredEvents(String query);

    EventBO updateEvent(Long id, EventBO eventBO);
}
