package adeo.leroymerlin.cdp.mapper;

import adeo.leroymerlin.cdp.business.EventBO;
import adeo.leroymerlin.cdp.dto.EventDTO;
import adeo.leroymerlin.cdp.entity.Event;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// Condition pure JAVA sinon utilisation de MapStruct
public class EventMapper {
    public static EventDTO mapToDTO(EventBO event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setImgUrl(event.getImgUrl());
        eventDTO.setComment(event.getComment());
        eventDTO.setNbStars(event.getNbStars());
        eventDTO.setBands(BandMapper.mapToDTOs(event.getBands()));
        return eventDTO;
    }

    public static EventBO mapEntityToBO(Event event) {
        EventBO eventBO = new EventBO();
        eventBO.setId(event.getId());
        eventBO.setTitle(event.getTitle());
        eventBO.setImgUrl(event.getImgUrl());
        eventBO.setComment(event.getComment());
        eventBO.setNbStars(event.getNbStars());
        eventBO.setBands(BandMapper.mapEntitiesToBOs(event.getBands()));
        return eventBO;
    }

    public static EventBO mapDTOToBO(EventDTO event) {
        EventBO eventBO = new EventBO();
        eventBO.setId(event.getId());
        eventBO.setTitle(event.getTitle());
        eventBO.setImgUrl(event.getImgUrl());
        eventBO.setComment(event.getComment());
        eventBO.setNbStars(event.getNbStars());
        eventBO.setBands(BandMapper.mapDTOsToBOs(event.getBands()));
        return eventBO;
    }

    public static Event mapToEntity(EventBO eventBO) {
        Event event = new Event();
        event.setId(eventBO.getId());
        event.setTitle(eventBO.getTitle());
        event.setImgUrl(eventBO.getImgUrl());
        event.setComment(eventBO.getComment());
        event.setNbStars(eventBO.getNbStars());
        event.setBands(BandMapper.mapToEntities(eventBO.getBands()));
        return event;
    }

    public static List<EventDTO> mapToDTOs(List<EventBO> events) {
        if (events == null || events.isEmpty()) {
            return Collections.emptyList();
        }
        return events.stream()
                .map(EventMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public static List<EventBO> mapEntitiesToBOs(List<Event> events) {
        if (events == null || events.isEmpty()) {
            return Collections.emptyList();
        }
        return events.stream()
                .map(EventMapper::mapEntityToBO)
                .collect(Collectors.toList());
    }

    public static List<EventBO> mapDTOsToBOs(List<EventDTO> events) {
        if (events == null || events.isEmpty()) {
            return Collections.emptyList();
        }
        return events.stream()
                .map(EventMapper::mapDTOToBO)
                .collect(Collectors.toList());
    }

    public static List<Event> mapToEntities(List<EventBO> events) {
        if (events == null || events.isEmpty()) {
            return Collections.emptyList();
        }
        return events.stream()
                .map(EventMapper::mapToEntity)
                .collect(Collectors.toList());
    }
}
