package com.cursospring.batch.multipledatabasejobs.config;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class BasicConfiguration {

    private final JobRepository repository;

    @Bean
    public JobLauncher simpleJobLauncher() {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(repository);
        return launcher;
    }
}
