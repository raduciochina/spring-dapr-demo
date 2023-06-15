package com.example.notificationhub.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final DataChecker dataChecker;

    public Scheduler(DataChecker dataChecker) {
        this.dataChecker = dataChecker;
    }

    @Scheduled(fixedRate = 6000)
    public void checkDataPeriodically() throws JsonProcessingException {
        dataChecker.checkData();
    }
}
