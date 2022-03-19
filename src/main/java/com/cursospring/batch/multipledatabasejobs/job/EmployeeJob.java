package com.cursospring.batch.multipledatabasejobs.job;

import com.cursospring.batch.multipledatabasejobs.dto.EmployeeDTO;
import com.cursospring.batch.multipledatabasejobs.mapper.EmployeeDBRowMapper;
import com.cursospring.batch.multipledatabasejobs.model.Employee;
import com.cursospring.batch.multipledatabasejobs.processor.EmployeeProcessor;
import com.cursospring.batch.multipledatabasejobs.utils.Constants;
import com.cursospring.batch.multipledatabasejobs.utils.SqlScripts;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class EmployeeJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier(value = Constants.SQL_SERVER_CONFIG)
    private DataSource sqlServerDatasource;

    @Autowired
    @Qualifier(value = Constants.POSTGRES_CONFIG)
    private DataSource postgresDatasource;

    @Autowired
    private EmployeeProcessor employeeProcessor;

    @Bean
    @Qualifier(value = "employeeJobUpdate")
    public Job employeeDatabaseJob() throws Exception {
        return jobBuilderFactory.get("employeeJobUpdate").start(employeeDatabaseStep()).build();
    }

    private Step employeeDatabaseStep() throws Exception {
        return stepBuilderFactory.get("employeeStep")
                .<EmployeeDTO, Employee>chunk(Constants.CHUNK_SIZE)
                .reader(employeeSqlReader())
                .processor(employeeProcessor)
                .writer(employeeSqlWriter())
                .build();
    }

    @Bean
    public ItemStreamReader<EmployeeDTO> employeeSqlReader() {
        JdbcCursorItemReader<EmployeeDTO> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(sqlServerDatasource);
        reader.setSql(SqlScripts.ALL_EMPLOYEES);
        reader.setRowMapper(new EmployeeDBRowMapper());
        return reader;
    }

    @Bean
    public JdbcBatchItemWriter<Employee> employeeSqlWriter() {
        JdbcBatchItemWriter<Employee> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql(SqlScripts.NEW_EMPLOYEE);
        writer.setDataSource(postgresDatasource);
        return writer;
    }
}
