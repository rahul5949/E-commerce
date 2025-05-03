package com.nagarro.emailService.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.emailService.dtos.SendEmailEventDto;
import com.nagarro.emailService.utils.EmailUtil;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
@AllArgsConstructor
public class SendEmailEventConsumer {
    private final ObjectMapper objectMapper;
    @KafkaListener(topics = "send-email-topic", groupId = "email-service-group-v2")
    public void handleSendEmailEvent(String message) {
        try {
            System.out.println("Received message: " + message);
            SendEmailEventDto event = objectMapper.readValue(message, SendEmailEventDto.class);
            String to = event.getTo();
            String body = event.getBody();
            String subject = event.getSubject();
            String from = event.getFrom();
            System.out.println("Sending email to: " + to);

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("emailservicesender5949@gmail.com", "vdirsyresokzuash");
                }
            };
            Session session = Session.getInstance(props, auth);

            EmailUtil.sendEmail(session, to,subject, body);

        } catch (Exception e) {
            System.out.println("ERROR while consuming message: " + message);
            e.printStackTrace();
        }
    }


}
