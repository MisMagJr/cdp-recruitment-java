package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.business.EventBO;
import adeo.leroymerlin.cdp.entity.Band;
import adeo.leroymerlin.cdp.entity.Event;
import adeo.leroymerlin.cdp.entity.Member;
import adeo.leroymerlin.cdp.exception.ResourceNotFoundException;
import adeo.leroymerlin.cdp.mapper.EventMapper;
import adeo.leroymerlin.cdp.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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
    private List<Event> eventList;

    @BeforeEach
    void setUp() {
        Member member = new Member();
        member.setId(1L);
        member.setName("Saad EDERDIK");
        Member member2 = new Member();
        member2.setId(2L);
        member2.setName("Spiderman");
        Member member3 = new Member();
        member3.setId(3L);
        member3.setName("Darius");
        Member member4 = new Member();
        member4.setId(4L);
        member4.setName("Swain");

        Band band = new Band();
        band.setId(1L);
        band.setName("Trolls");
        band.setMembers(Set.of(member, member2));
        Band band2 = new Band();
        band2.setId(2L);
        band2.setName("Noxus");
        band2.setMembers(Set.of(member3, member4));

        testEvent = new Event();
        testEvent.setId(eventId);
        testEvent.setTitle("Event Test");
        testEvent.setComment("Comment");
        testEvent.setBands(Set.of(band));

        Event testEvent2 = new Event();
        testEvent2.setId(2L);
        testEvent2.setTitle("Event Test 2");
        testEvent2.setComment("Comment");
        testEvent2.setBands(Set.of(band2));

        eventList = List.of(testEvent2, testEvent);
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
    void deleteEventTest_DoesntExist() {
        when(eventRepository.existsById(eventId)).thenReturn(false);

        assertThatThrownBy(() -> eventService.deleteEvent(eventId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Event with id " + eventId + " not found");

        verify(eventRepository, times(1)).existsById(eventId);
    }

    @Test
    void getFilteredEvents() {
        when(eventRepository.findAll()).thenReturn(eventList);

        List<EventBO> filteredEvents = eventService.getFilteredEvents("Sa");

        assertThat(filteredEvents)
                .hasSize(1)
                .first()
                .extracting(EventBO::getId, EventBO::getComment)
                .containsExactly(eventId, "Comment");
        verify(eventRepository).findAll();
    }

    @Test
    void updateEventTest() {
        EventBO eventBO = EventMapper.mapEntityToBO(testEvent);
        eventBO.setNbStars(3);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(testEvent));
        when(eventRepository.save(any(Event.class))).then(AdditionalAnswers.returnsFirstArg());

        EventBO result = eventService.updateEvent(eventId, eventBO);

        assertThat(result).isNotNull()
                .extracting(EventBO::getId, EventBO::getComment, EventBO::getTitle, EventBO::getImgUrl, EventBO::getNbStars)
                .containsExactly(eventBO.getId(), eventBO.getComment(), eventBO.getTitle(), eventBO.getImgUrl(), eventBO.getNbStars());
        verify(eventRepository, times(1)).findById(eventId);
        verify(eventRepository, times(1)).save(any(Event.class));
    }
}