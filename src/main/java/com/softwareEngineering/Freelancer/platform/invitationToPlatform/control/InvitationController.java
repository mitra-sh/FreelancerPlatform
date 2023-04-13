package com.softwareEngineering.Freelancer.platform.invitationToPlatform.control;

import com.softwareEngineering.Freelancer.platform.common.controller.BaseController;
import com.softwareEngineering.Freelancer.platform.request.SendingEmailRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
@RestController
@CrossOrigin
public class InvitationController extends BaseController {

    @RequestMapping("/invitation")
    public ResponseEntity sendEmailInvitation(@RequestBody SendingEmailRequest request){
        try {
            baseService.sendInvitation(request.getRecipientEmail());
            baseService.log(request.getUsername(), "sending invitation email","email of receiver is: "+request.getRecipientEmail());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Invitation sent to " + request.getRecipientEmail());
        }catch (MessagingException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.toString());
        }
    }
}
