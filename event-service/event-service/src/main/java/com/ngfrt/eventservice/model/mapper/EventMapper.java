package com.ngfrt.eventservice.model.mapper;

import com.ngfrt.eventservice.model.dto.EventDTO;
import com.ngfrt.eventservice.model.dto.UpdateEventDTO;
import com.ngfrt.eventservice.model.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventMapper {

    EventDTO toDto(Event event);
    Event toEntity(EventDTO eventDTO);

    @Mapping(target = "uuid", ignore = true)
    Event updateEntityFromDTO(@MappingTarget Event event, UpdateEventDTO updateEventDTO);

}

