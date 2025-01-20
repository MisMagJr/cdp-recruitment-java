package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.dto.EventDTO;
import adeo.leroymerlin.cdp.mapper.EventMapper;
import adeo.leroymerlin.cdp.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private static final Logger log = LoggerFactory.getLogger(EventController.class); // No Lombok or else would've used this annotation: @Slf4j
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/")
    public List<EventDTO> findEvents() {
        log.info("Controller Log - Handling GET request for /api/events - Request to retrieve all events");
        List<EventDTO> events = EventMapper.mapToDTOs(eventService.getEvents());
        log.debug("Controller Log - Successfully retrieved {} events for /api/events", events.size());
        return events;

    }

    @GetMapping(value = "/search/{query}")
    public List<EventDTO> findEvents(@PathVariable String query) {
        log.info("Controller Log - Handling GET request for /api/events/search/{} - Filtering with query: {}", query, query);
        List<EventDTO> events = EventMapper.mapToDTOsWithCount(eventService.getFilteredEvents(query));
        log.debug("Controller Log - Search complete for /api/events/search/{} - Found {} events matching the query", query, events.size());
        return events;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEvent(@PathVariable Long id) {
        log.info("Controller Log - Processing DELETE request for /api/events/{} - Request to delete event with id {}", id, id);
        eventService.deleteEvent(id);
        log.debug("Controller Log - Event with id {} deleted successfully from /api/events", id);
    }

    @PutMapping(value = "/{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody EventDTO event) { // Same EventDTO for request and response, could've created another object for requestBody to separate
        log.info("Controller Log - Handling PUT request for /api/events/{} - Request to update event with id {}", id, id);
        log.debug("Controller Log - Request body for update: {}", event);
        eventService.updateEvent(id, EventMapper.mapDTOToBO(event));
        log.info("Controller Log - Event with id {} successfully updated in /api/events", id);
    }
}
