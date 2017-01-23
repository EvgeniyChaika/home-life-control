package com.control.life.home.services.impl;

import com.control.life.home.custom.exception.ValidationException;
import com.control.life.home.dao.EmployeeDAO;
import com.control.life.home.models.Employee;
import com.control.life.home.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created on 21.03.16.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDao;

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findAll(Long id) {
        return employeeDao.findAll(id);
    }

    @Transactional
    @Override
    public void updateEmployee(Employee employee) throws ValidationException {
        employeeDao.updateEmployee(employee);
    }

    @Transactional
    @Override
    public void removeEmployee(Long idEmployee) {
        employeeDao.removeEmployee(idEmployee);
    }

    @Transactional(readOnly = true)
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeDao.getEmployeeById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean getEmployeeByEmail(String email) {
        return employeeDao.getEmployeeByEmail(email);
    }
}
