package com.asif.EmailsenderSpringBootOauth.services;

import com.asif.EmailsenderSpringBootOauth.model.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("spring.mail.username")
    private String sender;

    public String sendSimpleEmail(EmailDetails emailDetails){
        try {
            SimpleMailMessage mailMessage=new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setSubject(emailDetails.getSubject());
            mailMessage.setText(emailDetails.getMessage());
            javaMailSender.send(mailMessage);
            return "Mail sent SuccesFully";
        } catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}
