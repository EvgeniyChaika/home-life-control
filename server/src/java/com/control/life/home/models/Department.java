package com.control.life.home.models;

import com.control.life.home.utils.UniqueCheckAnnotation;
import com.google.gson.annotations.Expose;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created on 1/17/17.
 */

@Entity
@Table(name = "departments")
public class Department implements Serializable {

    @Id
    @Column(name = "id_department", nullable = true)
    @GeneratedValue
    private Long idDepartment;

    @Column(name = "department_name", unique = true)
    @NotNull(message = "Name can not be is null")
    @NotEmpty(message = "Name can not be is empty")
    @Length(max = 30, message = "Name length should not exceed 30 characters")
    @CheckWith(value = UniqueCheckAnnotation.class, message = "Department with the same name already exists")
    private String departmentName;

    @Expose
    @OneToMany(targetEntity = Employee.class, cascade = CascadeType.ALL, mappedBy = "department")
    private List<Employee> employeeList;

    public Long getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Long idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
