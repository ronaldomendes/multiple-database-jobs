package com.cursospring.batch.multipledatabasejobs.runner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.cursospring.batch.multipledatabasejobs.utils.Constants.CONTEXT_KEY_NAME;

@Component
@AllArgsConstructor
@Slf4j
public class JobRunner {

    private final JobLauncher jobLauncher;
    private final Job job;

    @Async
    public void runBatchJob() {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addString(CONTEXT_KEY_NAME, "updating_databases");
        builder.addDate("date", new Date(), true);
        runJob(job, builder.toJobParameters());
    }

    private void runJob(Job job, JobParameters jobParameters) {
        try {
            JobExecution execution = jobLauncher.run(job, jobParameters);
            log.info("Executing: {}", execution.toString());
        } catch (JobExecutionAlreadyRunningException e) {
            log.error("Job with fileName: {} is already running.", jobParameters.getParameters().get(CONTEXT_KEY_NAME));
        } catch (JobRestartException e) {
            log.error("Job with fileName: {} was not restarted.", jobParameters.getParameters().get(CONTEXT_KEY_NAME));
        } catch (JobInstanceAlreadyCompleteException e) {
            log.error("Job with fileName: {} already completed.", jobParameters.getParameters().get(CONTEXT_KEY_NAME));
        } catch (JobParametersInvalidException e) {
            log.error("Invalid job parameters: {}.", jobParameters.getParameters().get(CONTEXT_KEY_NAME));
        }
    }
}
