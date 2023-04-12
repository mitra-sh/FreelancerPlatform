package com.softwareEngineering.Freelancer.platform.request;

import lombok.Getter;
import lombok.Setter;

public class SendingEmailRequest {
    @Setter @Getter
    private String recipientEmail;
    @Setter @Getter
    private String username;
}
