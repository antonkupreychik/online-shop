package com.kupreychik.schedulerservice.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleJobService {

    private final JobScheduler jobScheduler;
    private final KafkaService kafkaService;

    @Value("${application.daily-stats}")
    String cronExpression;

    @PostConstruct
    public void init() {
        jobScheduler.scheduleRecurrently(cronExpression, kafkaService::sendCalculateStatisticsEvent);
    }
}
