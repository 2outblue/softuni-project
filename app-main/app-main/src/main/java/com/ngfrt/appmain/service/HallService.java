package com.ngfrt.appmain.service;


import com.ngfrt.appmain.model.dto.HallDTO;
import com.ngfrt.appmain.model.dto.HallDetailsDTO;
import com.ngfrt.appmain.model.dto.HallListingDTO;
import com.ngfrt.appmain.model.entity.Hall;
import com.ngfrt.appmain.model.mapper.HallMapper;
import com.ngfrt.appmain.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HallService {

    private HallRepository hallRepository;
    private HallMapper hallMapper;

    @Autowired
    public HallService(HallRepository hallRepository, HallMapper hallMapper) {
        this.hallRepository = hallRepository;
        this.hallMapper = hallMapper;
    }


    public HallDetailsDTO getHallByNumber(int id) {
        Hall hallEntity = hallRepository.getByNumber(id);

        return hallMapper.toHallDetailsDTO(hallEntity);
    }

    public String getHallNameByUuid(UUID uuid) {
        return hallRepository.getByUuid(uuid).getName();
    }

    public List<HallListingDTO> getAllHallsForListing() {
        return hallRepository.findAll().stream().map(hallMapper::toHallListingDTO)
                .collect(Collectors.toList());
    }
}

