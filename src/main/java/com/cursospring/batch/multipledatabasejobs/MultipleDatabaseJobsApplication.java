package com.cursospring.batch.multipledatabasejobs;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableBatchProcessing
@EnableScheduling
@ComponentScan(basePackages = {"com.cursospring.batch.multipledatabasejobs"})
public class MultipleDatabaseJobsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleDatabaseJobsApplication.class, args);
	}

}
