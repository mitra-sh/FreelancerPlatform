package com.softwareEngineering.Freelancer.platform.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SearchTicketRequest {
    @Setter @Getter
    private List<String> skills;
}
