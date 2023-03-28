package com.softwareEngineering.Freelancer.platform.model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    public static void sendInvitation(String recipientEmail, String senderEmail, String senderPassword) throws MessagingException {

        // Mail server configuration
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Login to sender email account
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // Compose the message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject("Invitation");
        message.setText("Hello,\n\nYou have been invited to join our platform.\n\nBest regards,\nThe Freelancer Platform Team");

        // Send the message
        Transport.send(message);
    }
}
