package com.control.life.home.services.impl;

import com.control.life.home.custom.exception.ValidationException;
import com.control.life.home.dao.DepartmentDAO;
import com.control.life.home.models.Department;
import com.control.life.home.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created on 16.03.16.
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDAO departmentDao;

    @Transactional(readOnly = true)
    @Override
    public Collection findAll() {
        return departmentDao.findAll();
    }

    @Transactional()
    @Override
    public void updateDepartment(Department department) throws ValidationException {
        departmentDao.updateDepartment(department);
    }

    @Transactional
    @Override
    public void removeDepartment(Long idDepartment) {
        Department department = departmentDao.getDepartmentById(idDepartment);
        departmentDao.removeDepartment(department);
    }

    @Transactional(readOnly = true)
    @Override
    public Department getDeparmentById(Long id) {
        return departmentDao.getDepartmentById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean getDeparmentByName(String name) {
        return departmentDao.getDepartmentByName(name);
    }
}
