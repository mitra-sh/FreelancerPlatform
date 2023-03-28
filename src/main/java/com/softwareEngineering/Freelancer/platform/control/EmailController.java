package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @RequestMapping("/invitation/{recipientEmail}")
    public ResponseEntity sendEmailInvitation(@PathVariable String recipientEmail){
        try {
            EmailService.sendInvitation(recipientEmail);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Invitation sent to " + recipientEmail);
        }catch (MessagingException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.toString());
        }
    }
}
