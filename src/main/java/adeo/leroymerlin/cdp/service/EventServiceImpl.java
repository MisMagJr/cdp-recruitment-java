package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.business.EventBO;
import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.mapper.EventMapper;
import adeo.leroymerlin.cdp.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventBO> getEvents() {
        return EventMapper.mapEntitiesToBOs(eventRepository.findAll());
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public List<EventBO> getFilteredEvents(String query) {
        List<Event> events = eventRepository.findAll();
        // Filter the events list in pure JAVA here

        return EventMapper.mapEntitiesToBOs(events);
    }
}
