package com.ngfrt.appmain.model.mapper;

import com.ngfrt.appmain.model.dto.EventDTO;
import com.ngfrt.appmain.model.dto.EventInfoDTO;
import com.ngfrt.appmain.service.HallService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(source = "hallId", target = "hallName", qualifiedByName = "mapHallIdToHallName")
    EventInfoDTO toEventInfoDTO(EventDTO event, @Context HallService hallService);

    @Named("mapHallIdToHallName")
    public static String mapHallIdToHallName(UUID uuid, @Context HallService hallService) {
        return hallService.getHallNameByUuid(uuid);
    }
}
