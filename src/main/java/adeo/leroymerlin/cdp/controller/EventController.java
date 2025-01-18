package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.dto.EventDTO;
import adeo.leroymerlin.cdp.mapper.EventMapper;
import adeo.leroymerlin.cdp.service.EventServiceImpl;
import adeo.leroymerlin.cdp.entity.Event;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventServiceImpl eventService;

    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/")
    public List<EventDTO> findEvents() {
        return EventMapper.mapToDTOs(eventService.getEvents());
    }

    @GetMapping(value = "/search/{query}")
    public List<Event> findEvents(@PathVariable String query) {
        return eventService.getFilteredEvents(query);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @PutMapping(value = "/{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody Event event) {
    }
}
