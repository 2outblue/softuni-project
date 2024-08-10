package com.ngfrt.appmain.model.mapper;


import com.ngfrt.appmain.model.dto.HallDTO;
import com.ngfrt.appmain.model.dto.HallDetailsDTO;
import com.ngfrt.appmain.model.dto.HallListingDTO;
import com.ngfrt.appmain.model.entity.Hall;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HallMapper {

    HallDTO toHallDTO(Hall hall);
    HallDetailsDTO toHallDetailsDTO(Hall hall);

    HallListingDTO toHallListingDTO(Hall hall);
}
