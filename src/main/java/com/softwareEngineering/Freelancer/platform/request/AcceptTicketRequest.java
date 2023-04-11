package com.softwareEngineering.Freelancer.platform.request;

import lombok.Getter;
import lombok.Setter;

public class AcceptTicketRequest {
    @Setter @Getter
    private String username;
    @Setter @Getter
    private int ticketNumber;

}
