package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.business.EventBO;
import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.exception.ResourceNotFoundException;
import adeo.leroymerlin.cdp.mapper.EventMapper;
import adeo.leroymerlin.cdp.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventBO> getEvents() {
        return EventMapper.mapEntitiesToBOs(eventRepository.findAll());
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event with id " + id + " not found");
        }

        eventRepository.deleteById(id);
    }

    public List<EventBO> getFilteredEvents(String query) {
        List<Event> events = eventRepository.findAll();
        // Filter the events list in pure JAVA here

        return EventMapper.mapEntitiesToBOs(events);
    }

    @Override
    public EventBO updateEvent(Long id, EventBO eventBO) {
        eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event with id " + id + " not found"));

        // The resource we want to update is found in DB
        // Two options here: map the BO to an entity and set id and save or set/replace all fields of dbEvent with those of the request
        // since it's a PUT not a PATCH, we wanna replace whole resource with the data in the request not only replace provided so we can either map or create new entity and set fields and id
        // or just update all fields of dbEvent either they're null or not in eventBO
        Event newEvent = EventMapper.mapToEntity(eventBO);
        eventBO.setId(id); // Ensures that the id that we're updating is the same as the one in path and the one we verified in first line

        return EventMapper.mapEntityToBO(eventRepository.save(newEvent));
    }
}
