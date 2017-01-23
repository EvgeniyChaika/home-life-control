package com.control.life.home.services;


import com.control.life.home.custom.exception.ValidationException;
import com.control.life.home.models.Department;

import java.util.Collection;

/**
 * Created on 16.03.16.
 */
public interface DepartmentService {

    void removeDepartment(Long idDepartment);

    Department getDeparmentById(Long id);

    boolean getDeparmentByName(String name);

    Collection findAll();

    void updateDepartment(Department department) throws ValidationException;
}