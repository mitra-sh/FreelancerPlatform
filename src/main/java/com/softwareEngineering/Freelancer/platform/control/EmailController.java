package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.request.SendingEmailRequest;
import com.softwareEngineering.Freelancer.platform.service.AuditLogService;
import com.softwareEngineering.Freelancer.platform.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@CrossOrigin
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private AuditLogService auditLogService;

    @RequestMapping("/invitation")
    public ResponseEntity sendEmailInvitation(@RequestBody SendingEmailRequest request){
        try {
            EmailService.sendInvitation(request.getRecipientEmail());
            auditLogService.log(request.getUsername(), "sending invitation email","email of receiver is: "+request.getRecipientEmail());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Invitation sent to " + request.getRecipientEmail());
        }catch (MessagingException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.toString());
        }
    }
}
