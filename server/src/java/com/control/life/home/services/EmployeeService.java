package com.control.life.home.services;


import com.control.life.home.custom.exception.ValidationException;
import com.control.life.home.models.Employee;

import java.util.List;

/**
 * Created on 21.03.16.
 */
public interface EmployeeService {
    List<Employee> findAll(Long id);

    void removeEmployee(Long id);

    Employee getEmployeeById(Long id);

    boolean getEmployeeByEmail(String email);

    void updateEmployee(Employee employee) throws ValidationException;

}
