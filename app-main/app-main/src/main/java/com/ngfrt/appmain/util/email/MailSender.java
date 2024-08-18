package com.ngfrt.appmain.util.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MailSender {

    private final RestTemplate restTemplate;
    private final String emailServiceUrl;
    private final boolean serviceEnabled;

    @Autowired
    public MailSender(RestTemplate restTemplate,
                      @Value("${api.email-service.url}") String emailServiceUrl,
                      @Value("${api.email-service.enable-service}") boolean serviceEnabled) {
        this.restTemplate = restTemplate;
        this.emailServiceUrl = emailServiceUrl;
        this.serviceEnabled = serviceEnabled;
    }

    public boolean sendMail(String address, String subject, String message) {
        if (!serviceEnabled) {
            return true;
        }

        //This will obviously go bad if the user provides 'wrong' data when creating an event/ or buying ticket.
        String url = String.format("%s/%s/%s/%s",emailServiceUrl, address, subject, message);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, null, String.class);

        return response.getStatusCode().is2xxSuccessful();
    }
}
