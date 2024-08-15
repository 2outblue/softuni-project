package com.ngfrt.appmain.service;

import com.ngfrt.appmain.model.dto.EventDTO;
import com.ngfrt.appmain.model.dto.TicketDTO;
import com.ngfrt.appmain.model.entity.Ticket;
import com.ngfrt.appmain.model.mapper.TicketMapper;
import com.ngfrt.appmain.repository.HallRepository;
import com.ngfrt.appmain.repository.TicketRepository;
import com.ngfrt.appmain.util.email.MailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final HallRepository hallRepository;
    private final MailSender mailSender;
    private final String eventServiceUrl;
    private final RestTemplate restTemplate;

    public TicketService(TicketRepository ticketRepository,
                         TicketMapper ticketMapper,
                         HallRepository hallRepository,
                         MailSender mailSender,
                         @Value("${api.event.service.url}") String eventServiceUr, RestTemplate restTemplate) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.hallRepository = hallRepository;
        this.mailSender = mailSender;
        this.eventServiceUrl = eventServiceUr;
        this.restTemplate = restTemplate;
    }

    public void saveTicketAndSendEmail(TicketDTO ticketDTO, UUID eventUuid) {

        updateTicketNumber(eventUuid);
        Ticket ticketEntity = ticketMapper.toEntity(ticketDTO, hallRepository);
        ticketEntity.setUuid(UUID.randomUUID());
        //TODO - maybe implement a check if entity was saved - if not throw exception ?
        Ticket save = ticketRepository.save(ticketEntity);

        String email = emailBuilder(ticketEntity.getUuid(), ticketDTO);
        String subject = "Your Ticket at Cartland Convention Center";
        mailSender.sendMail(ticketDTO.getEmail().trim(), subject, email);
    }

    private String emailBuilder(UUID ticketUuid, TicketDTO ticket) {
        return String.format("Your ticket information:\n" +
                "Event name: %s\n" +
                "Date: %s\n" +
                "Hall: %s\n" +
                "Ticket Code: %s\n\n" +
                "Please show this to our staff at the entrance.\n" +
                "Enjoy the Event!\n" +
                "In case of any issues you can contact us at support@cartlandcc.support.com\n\n\n" +
                "Best regards,\n" +
                "The Cartland Convention Center",
                ticket.getEventName(), ticket.getEventDate().toString(), ticket.getHallName(), ticketUuid.toString());
    }

    private void updateTicketNumber(UUID uuid) {
        String url = eventServiceUrl + "/" + uuid + "/buyTicket";
        restTemplate.getForObject(url, EventDTO.class);
    }
}
