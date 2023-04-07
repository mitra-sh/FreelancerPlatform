package com.softwareEngineering.Freelancer.platform.request;

import lombok.Getter;
import lombok.Setter;

public class AcceptTicketRequest {
    @Setter @Getter
    public Long ticketNumber;
    @Setter @Getter
    public String username;
}
