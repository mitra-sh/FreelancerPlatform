package com.softwareEngineering.Freelancer.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneId;
import java.util.TimeZone;

@SpringBootApplication
public class FreelancerPlatformApplication {

    public static void main(String[] args) {
        ZoneId desiredTimeZone = ZoneId.of("America/Montreal"); // Replace with your desired time zone
        TimeZone.setDefault(TimeZone.getTimeZone(desiredTimeZone));
        SpringApplication.run(FreelancerPlatformApplication.class, args);
    }
}
