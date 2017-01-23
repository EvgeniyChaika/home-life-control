package com.control.life.home.utils;

import com.control.life.home.models.Department;
import com.control.life.home.models.Employee;
import com.control.life.home.services.DepartmentService;
import com.control.life.home.services.EmployeeService;
import com.control.life.home.services.impl.DepartmentServiceImpl;
import com.control.life.home.services.impl.EmployeeServiceImpl;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.stereotype.Component;

/**
 * Created on 1/17/17.
 */

@Component
public class UniqueCheckAnnotation implements CheckWithCheck.SimpleCheck {

    private DepartmentService departmentService = SpringUtils.getBean(DepartmentServiceImpl.class);

    private EmployeeService employeeService = SpringUtils.getBean(EmployeeServiceImpl.class);

    @Override
    public boolean isSatisfied(Object validatedObject, Object value) {
        if (validatedObject instanceof Department) {
            Department department;
            Long idD = ((Department) validatedObject).getIdDepartment();
            if (idD != null) {
                department = departmentService.getDeparmentById(idD);
                if (value.toString().equals(department.getDepartmentName())) {
                    return true;
                }
            }
            if (departmentService.getDeparmentByName(value.toString())) {
                return false;
            }
        } else {
            if (validatedObject instanceof Employee) {
                Employee employee;
                Long idE = ((Employee) validatedObject).getEmployeeId();
                if (idE != null) {
                    employee = employeeService.getEmployeeById(idE);
                    if (value.toString().equals(employee.getEmployeeEmail())) {
                        return true;
                    }
                }
                if (employeeService.getEmployeeByEmail(value.toString())) {
                    return false;
                }
            }
        }
        return true;
    }
}
