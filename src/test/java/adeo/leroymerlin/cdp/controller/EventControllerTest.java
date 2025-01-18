package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.business.EventBO;
import adeo.leroymerlin.cdp.dto.EventDTO;
import adeo.leroymerlin.cdp.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


// TODO at the end
@ExtendWith(MockitoExtension.class)
class EventControllerTest {
    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @Test
    void findEvents() {
    }

    @Test
    void testFindEvents() {
    }

    @Test
    void deleteEventTest() {
        Long eventId = 1L;

        eventController.deleteEvent(eventId);

        verify(eventService, times(1)).deleteEvent(eventId);
    }

    @Test
    void updateEventTest() {
        Long eventId = 1L;
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventId);
        eventDTO.setTitle("Test Title");

        eventController.updateEvent(eventId, eventDTO);

        verify(eventService, times(1)).updateEvent(eq(1L), any(EventBO.class));
    }
}