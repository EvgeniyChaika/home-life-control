package com.control.life.home.services.batch;

import com.control.life.home.models.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created on 06.02.17.
 */

public class DepartmentRowMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        Department department = new Department();
        department.setIdDepartment(resultSet.getLong("id_department"));
        department.setDepartmentName(resultSet.getString("department_name"));
        return department;
    }
}
