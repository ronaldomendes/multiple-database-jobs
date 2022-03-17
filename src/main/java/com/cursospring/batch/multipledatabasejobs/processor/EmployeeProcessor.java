package com.cursospring.batch.multipledatabasejobs.processor;

import com.cursospring.batch.multipledatabasejobs.dto.EmployeeDTO;
import com.cursospring.batch.multipledatabasejobs.model.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProcessor implements ItemProcessor<EmployeeDTO, Employee> {

    @Override
    public Employee process(EmployeeDTO dto) throws Exception {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstname(dto.getFirstname());
        employee.setLastname(dto.getLastname());
        employee.setEmail(dto.getEmail());
        employee.setGender(dto.getGender());
        employee.setProfession(dto.getProfession());
        employee.setAge(dto.getAge());
        return employee;
    }
}
