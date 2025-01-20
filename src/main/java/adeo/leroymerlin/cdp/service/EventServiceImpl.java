package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.business.EventBO;
import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.exception.ResourceNotFoundException;
import adeo.leroymerlin.cdp.mapper.EventMapper;
import adeo.leroymerlin.cdp.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class); // No Lombok or else would've used this annotation: @Slf4j

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventBO> getEvents() {
        logger.info("Retrieving all events.");
        List<EventBO> events = EventMapper.mapEntitiesToBOs(eventRepository.findAll());
        logger.debug("Returned {} events.", events.size());
        return events;
    }

    public void deleteEvent(Long id) {
        logger.info("Attempting to delete event with id: {}", id);
        if (!eventRepository.existsById(id)) {
            logger.error("Event with id {} not found.", id);
            throw new ResourceNotFoundException("Event with id " + id + " not found");
        }

        eventRepository.deleteById(id);
        logger.info("Event with id {} successfully deleted.", id);
    }

    public List<EventBO> getFilteredEvents(String query) {
        // Filter the events list in pure JAVA here
        logger.info("Filtering events with query: '{}'", query);
        List<EventBO> filteredEvents = EventMapper
                .mapEntitiesToBOs(eventRepository.findAll())
                .stream()
                .filter(eventBO -> eventBO.hasBandWithMemberNamePattern(query))
                .toList();
        logger.debug("Filtered events list size: {}", filteredEvents.size());

        return filteredEvents;
    }

    @Override
    public EventBO updateEvent(Long id, EventBO eventBO) {
        logger.info("Updating event with id {}", id);
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event with id " + id + " not found");
        }

        // The resource we want to update is found in DB
        // Two options here: map the BO to an entity and set id and save or set/replace all fields of dbEvent with those of the request
        // since it's a PUT not a PATCH, we wanna replace whole resource with the data in the request not only replace provided so we can either map or create new entity and set fields and id
        // or just update all fields of dbEvent either they're null or not in eventBO
        Event newEvent = EventMapper.mapToEntity(eventBO);
        eventBO.setId(id); // Ensures that the id that we're updating is the same as the one in path and the one we verified in first line

        EventBO savedEvent = EventMapper.mapEntityToBO(eventRepository.save(newEvent));
        logger.info("Event with id: {} successfully updated.", id);

        return savedEvent;
    }
}
