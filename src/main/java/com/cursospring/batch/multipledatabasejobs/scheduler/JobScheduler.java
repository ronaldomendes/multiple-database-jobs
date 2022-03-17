package com.cursospring.batch.multipledatabasejobs.scheduler;

import com.cursospring.batch.multipledatabasejobs.runner.JobRunner;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@AllArgsConstructor
@Slf4j
public class JobScheduler {

    private final JobRunner runner;

    @Scheduled(cron = "0 0/2 * 1/1 * ?")
    public void jobScheduled() {
        log.info("Initializing job scheduler");
        runner.runBatchJob();
    }
}
