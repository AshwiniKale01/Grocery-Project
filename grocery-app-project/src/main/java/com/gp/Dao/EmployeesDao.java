package com.gp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gp.beans.Employees;

public interface EmployeesDao extends JpaRepository<Employees, Integer> {

}
