package com.ngfrt.appmain.model.mapper;

import com.ngfrt.appmain.model.dto.EventCalendarDTO;
import com.ngfrt.appmain.model.dto.EventDTO;
import com.ngfrt.appmain.model.dto.EventEditDTO;
import com.ngfrt.appmain.model.dto.EventInfoDTO;
import com.ngfrt.appmain.service.HallService;
import jdk.jfr.Name;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(source = "hallId", target = "hallName", qualifiedByName = "mapHallIdToHallName")
    @Mapping(source = "hallId", target = "hallUuid")
    EventInfoDTO toEventInfoDTO(EventDTO event, @Context HallService hallService);

    @Mapping(source = "date", target = "dayOfMonth", qualifiedByName = "mapDateToDayOfMonth")
    EventCalendarDTO toEventCalendarDTO(EventDTO event);

    @Mapping(source = "hallId", target = "hallName", qualifiedByName = "mapHallIdToHallName")
    EventEditDTO toEventEditDTO(EventDTO eventDTO, @Context HallService hallService);

    @Named("mapHallIdToHallName")
    static String mapHallIdToHallName(UUID uuid, @Context HallService hallService) {
        return hallService.getHallNameByUuid(uuid);
    }

    @Named("mapDateToDayOfMonth")
    default int mapDateToDayOfMonth(LocalDate eventDate) {
        return eventDate != null ? eventDate.getDayOfMonth() : 0;
    }

}
