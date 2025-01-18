package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.dto.EventDTO;
import adeo.leroymerlin.cdp.mapper.EventMapper;
import adeo.leroymerlin.cdp.service.EventService;
import adeo.leroymerlin.cdp.service.EventServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/")
    public List<EventDTO> findEvents() {
        return EventMapper.mapToDTOs(eventService.getEvents());
    }

    @GetMapping(value = "/search/{query}")
    public List<EventDTO> findEvents(@PathVariable String query) {
        return EventMapper.mapToDTOs(eventService.getFilteredEvents(query));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @PutMapping(value = "/{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody EventDTO event) { // Same EventDTO for request and response, could've created another object for requestBody to separate
        eventService.updateEvent(id, EventMapper.mapDTOToBO(event));
    }
}
