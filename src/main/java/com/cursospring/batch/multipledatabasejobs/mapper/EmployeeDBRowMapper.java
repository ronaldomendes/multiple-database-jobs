package com.cursospring.batch.multipledatabasejobs.mapper;

import com.cursospring.batch.multipledatabasejobs.dto.EmployeeDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EmployeeDBRowMapper implements RowMapper<EmployeeDTO> {

    @Override
    public EmployeeDTO mapRow(ResultSet set, int rowNum) throws SQLException {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(set.getLong("id"));
        dto.setFirstname(set.getString("firstname"));
        dto.setLastname(set.getString("lastname"));
        dto.setEmail(set.getString("email"));
        dto.setGender(set.getString("gender"));
        dto.setProfession(set.getString("profession"));
        dto.setAge(set.getInt("age"));
        return dto;
    }
}
