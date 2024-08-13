package com.ngfrt.appmain.model.mapper;


import com.ngfrt.appmain.model.dto.TicketDTO;
import com.ngfrt.appmain.model.entity.Hall;
import com.ngfrt.appmain.model.entity.Ticket;
import com.ngfrt.appmain.repository.HallRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(source = "hallUuid", target = "hall", qualifiedByName = "mapUuidToHall")
    Ticket toEntity(TicketDTO ticketDTO, @Context HallRepository hallRepository);

    TicketDTO toDTO(Ticket ticket);

    @Named("mapUuidToHall")
    default Hall mapUuidToHall(UUID hallUuid, @Context HallRepository hallRepository) {
        return hallRepository.getByUuid(hallUuid);
    }
}
