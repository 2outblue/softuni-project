package com.ngfrt.emailservice.util;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import static jakarta.mail.Message.RecipientType.TO;

@Component
public class MailSender {

    private final Gmail service;

    @Autowired
    public MailSender(Gmail service) {
        this.service = service;
    }

    public void sendMail(String from, String to, String subject, String message) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        try {
            email.setFrom(new InternetAddress(from));
            email.addRecipient(TO, new InternetAddress(to));
            email.setSubject(subject);
            email.setText(message);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            email.writeTo(buffer);
            byte[] rawMessageBytes = buffer.toByteArray();
            String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
            Message msg = new Message();
            msg.setRaw(encodedEmail);

            msg = service.users().messages().send("me", msg).execute();
            System.out.println("Message id: " + msg.getId());
            System.out.println(msg.toPrettyString());

        } catch (IOException | MessagingException e) {

            throw new MailSendException("Unable to send email");
//            GoogleJsonError error = e.getDetails();
//            if (error.getCode() == 403) {
//                System.err.println("Unable to send message: " + e.getDetails());
//            } else {
//                throw e;
//            }
        }
    }
}

