package com.softwareEngineering.Freelancer.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailService {

    public static void sendInvitation(String recipientEmail) throws MessagingException {
            EmailServiceHelper.sendInvitation(recipientEmail, "freelancer.platform.6311@gmail.com", "wuqnufztuwxrkvak");
            System.out.println("Invitation email sent successfully.");
    }
}

