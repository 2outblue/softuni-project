package com.ngfrt.emailservice.service;

import com.ngfrt.emailservice.util.MailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final MailSender mailSender;
    private final String senderAddress;

    public EmailService(MailSender mailSender,
                        @Value("${api.email.sender-address}") String senderAddress) {
        this.mailSender = mailSender;
        this.senderAddress = senderAddress;
    }


    public void sendEmail(){
        mailSender.sendMail(senderAddress, "affngfrt@gmail.com", "Spring Testing", "testingggggg");
    }

    public void sendEmail(String address, String subject, String message){
        mailSender.sendMail(senderAddress, address, subject, message);
    }
}
