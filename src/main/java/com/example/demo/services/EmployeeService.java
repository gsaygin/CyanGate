package com.example.demo.services;

import com.example.demo.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(long id);
    Employee insert(Employee p);
    boolean delete(long id);
    boolean update(Employee p);
}
