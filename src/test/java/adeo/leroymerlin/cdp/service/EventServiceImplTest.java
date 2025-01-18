package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.business.EventBO;
import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.mapper.EventMapper;
import adeo.leroymerlin.cdp.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


// TODO at the end
@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {
    @Mock
    private EventRepository eventRepository;
    @InjectMocks
    private EventServiceImpl eventService;

    private Long eventId = 1L;
    private Event testEvent;

    @BeforeEach
    void setUp() {
        testEvent = new Event();
        testEvent.setId(eventId);
        testEvent.setTitle("Event Test");
        testEvent.setComment("Comment");
    }

    @Test
    void getEvents() {
    }

    @Test
    void deleteEventTest() {
        when(eventRepository.existsById(eventId)).thenReturn(true);

        eventService.deleteEvent(eventId);

        verify(eventRepository, times(1)).existsById(eventId);
        verify(eventRepository, times(1)).deleteById(eventId);
    }

    @Test
    void getFilteredEvents() {
    }

    @Test
    void updateEventTest() {
        EventBO eventBO = EventMapper.mapEntityToBO(testEvent);
        eventBO.setNbStars(3);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(testEvent));
        when(eventRepository.save(any(Event.class))).then(AdditionalAnswers.returnsFirstArg());

        EventBO result = eventService.updateEvent(eventId, eventBO);

        assertThat(result).isNotNull()
                .extracting(EventBO::getId, EventBO::getComment, EventBO::getTitle, EventBO::getBands, EventBO::getImgUrl, EventBO::getNbStars)
                .containsExactly(eventBO.getId(), eventBO.getComment(), eventBO.getTitle(), eventBO.getBands(), eventBO.getImgUrl(), eventBO.getNbStars());
        verify(eventRepository, times(1)).findById(eventId);
        verify(eventRepository, times(1)).save(any(Event.class));
    }
}