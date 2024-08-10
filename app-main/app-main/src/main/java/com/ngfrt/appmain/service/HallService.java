package com.ngfrt.appmain.service;


import com.ngfrt.appmain.model.dto.HallDTO;
import com.ngfrt.appmain.model.dto.HallDetailsDTO;
import com.ngfrt.appmain.model.entity.Hall;
import com.ngfrt.appmain.model.mapper.HallMapper;
import com.ngfrt.appmain.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

