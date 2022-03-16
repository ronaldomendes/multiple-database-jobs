package com.cursospring.batch.multipledatabasejobs;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class MultipleDatabaseJobsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleDatabaseJobsApplication.class, args);
	}

}
