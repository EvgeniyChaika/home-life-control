package com.control.life.home.dao;


import com.control.life.home.models.Department;

import java.util.Collection;

/**
 * Created on 18.03.16.
 */
public interface DepartmentDAO {

    void updateDepartment(Department department);

    Collection findAll();

    void removeDepartment(Department department);

    Department getDepartmentById(Long id);

    boolean getDepartmentByName(String name);
}