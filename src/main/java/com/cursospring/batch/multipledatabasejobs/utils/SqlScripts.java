package com.cursospring.batch.multipledatabasejobs.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlScripts {

    public static final String NEW_EMPLOYEE = "insert into tb_employees (id, firstname, lastname, email, gender, " +
            "profession, age) values (:id, :firstname, :lastname, :email, :gender, :profession, :age)";
    public static final String ALL_EMPLOYEES = "select * from tb_employees";
}
