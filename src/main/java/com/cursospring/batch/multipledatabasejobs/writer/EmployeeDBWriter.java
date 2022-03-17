package com.cursospring.batch.multipledatabasejobs.writer;

import com.cursospring.batch.multipledatabasejobs.model.Employee;
import com.cursospring.batch.multipledatabasejobs.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
@Slf4j
public class EmployeeDBWriter implements ItemWriter<Employee> {

    @Autowired
    @Qualifier(value = Constants.POSTGRES_CONFIG)
    private DataSource dataSource;

    @Override
    public void write(List<? extends Employee> employees) throws Exception {
        JdbcBatchItemWriter<Employee> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        employees.forEach(e -> log.info("Employee={}", e.toString()));
        log.info("Writer={}", writer.toString());
    }
}
