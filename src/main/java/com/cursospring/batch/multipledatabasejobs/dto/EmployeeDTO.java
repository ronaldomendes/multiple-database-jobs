package com.cursospring.batch.multipledatabasejobs.dto;

import lombok.Data;

@Data
public class EmployeeDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String gender;
    private String profession;
    private int age;
}
