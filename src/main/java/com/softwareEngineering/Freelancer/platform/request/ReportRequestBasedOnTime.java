package com.softwareEngineering.Freelancer.platform.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReportRequestBasedOnTime {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime beginningTime;
}
