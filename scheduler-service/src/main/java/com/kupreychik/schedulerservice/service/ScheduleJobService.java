package com.kupreychik.schedulerservice.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleJobService {

    private final JobScheduler jobScheduler;
    private final KafkaService kafkaService;

    @PostConstruct
    public void init() {
        jobScheduler.scheduleRecurrently(Cron.daily(), kafkaService::sendCalculateStatisticsEvent);
    }
}
