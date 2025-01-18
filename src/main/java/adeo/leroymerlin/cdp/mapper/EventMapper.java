package adeo.leroymerlin.cdp.mapper;

import adeo.leroymerlin.cdp.business.EventBO;
import adeo.leroymerlin.cdp.dto.EventDTO;
import adeo.leroymerlin.cdp.entity.Event;

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

    public static EventBO mapToBO(Event event) {
        EventBO eventBO = new EventBO();
        eventBO.setId(event.getId());
        eventBO.setTitle(event.getTitle());
        eventBO.setImgUrl(event.getImgUrl());
        eventBO.setComment(event.getComment());
        eventBO.setNbStars(event.getNbStars());
        eventBO.setBands(BandMapper.mapToBOs(event.getBands()));
        return eventBO;
    }

    public static Event mepToEntity(EventBO eventBO) {
        Event event = new Event();
        event.setId(eventBO.getId());
        event.setTitle(eventBO.getTitle());
        event.setImgUrl(eventBO.getImgUrl());
        event.setComment(eventBO.getComment());
        event.setNbStars(eventBO.getNbStars());
        event.setBands(BandMapper.mapToEntities(eventBO.getBands()));
        return event;
    }
}
