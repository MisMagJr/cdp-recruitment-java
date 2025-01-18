package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.business.EventBO;
import adeo.leroymerlin.cdp.entity.Event;

import java.util.List;

public interface EventService {
    List<EventBO> getEvents();

    void deleteEvent(Long id);

    List<EventBO> getFilteredEvents(String query);
}
