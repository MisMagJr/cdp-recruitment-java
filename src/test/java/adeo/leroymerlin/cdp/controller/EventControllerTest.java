package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.business.EventBO;
import adeo.leroymerlin.cdp.dto.EventDTO;
import adeo.leroymerlin.cdp.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EventControllerTest {
    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @Test
    void findEvents() {
        EventBO eventBO1 = new EventBO();
        eventBO1.setId(1L);
        EventBO eventBO2 = new EventBO();
        eventBO2.setId(2L);
        when(eventService.getEvents()).thenReturn(List.of(eventBO1, eventBO2));

        List<EventDTO> eventDTOS = eventController.findEvents();

        assertThat(eventDTOS)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2);
        verify(eventService, times(1)).getEvents();
    }

    @Test
    void findEventsFilteredTest() {
        String query = "Sa";

        eventController.findEvents(query);

        verify(eventService, times(1)).getFilteredEvents(query);
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