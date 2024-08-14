package com.ngfrt.emailservice.web;

import com.ngfrt.emailservice.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    private final EmailService emailService;

    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send/{address}/{subject}/{message}")
    public ResponseEntity<String> sendEmail(@PathVariable("address") String address,
                                            @PathVariable("subject") String subject,
                                            @PathVariable("message") String message) {
        emailService.sendEmail(address, subject, message);

        return ResponseEntity.ok(String.format("Address: %s,\n Email subject: %s,\n Email content: %s", address, subject, message));
    }
}
