package com.ngfrt.appmain.service;

import com.ngfrt.appmain.model.dto.TicketDTO;
import com.ngfrt.appmain.model.entity.Ticket;
import com.ngfrt.appmain.model.mapper.TicketMapper;
import com.ngfrt.appmain.repository.HallRepository;
import com.ngfrt.appmain.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final HallRepository hallRepository;

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper, HallRepository hallRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.hallRepository = hallRepository;
    }

    public void saveTicket(TicketDTO ticketDTO) {
        Ticket ticketEntity = ticketMapper.toEntity(ticketDTO, hallRepository);
        ticketEntity.setUuid(UUID.randomUUID());

        Ticket save = ticketRepository.save(ticketEntity);

        //TODO - maybe implement a check if entity was saved - if not throw exception ?
    }
}
