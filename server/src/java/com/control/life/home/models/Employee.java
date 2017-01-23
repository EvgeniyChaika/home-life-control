package com.control.life.home.models;

import com.control.life.home.utils.UniqueCheckAnnotation;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created on 1/17/17.
 */

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @Column(name = "id_employee")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNegative(message = "ID can not be is negative")
    private Long employeeId;

    @Column(name = "employee_name")
    @NotNull(message = "Name can not be is null")
    @NotEmpty(message = "Name can not be is empty")
    @Length(max = 30, message = "Name length should not exceed 30 characters")
    private String employeeName;

    @Column(name = "employee_email")
    @NotNull(message = "Email can not be is null")
    @NotEmpty(message = "Email can not be is empty")
    @Length(max = 30, message = "Email length should not exceed 30 characters")
    @Email(message = "Email enter error")
    @CheckWith(value = UniqueCheckAnnotation.class, message = "Employee with the same email already exists")
    private String employeeEmail;

    @Column(name = "employee_salary")
    @NotNull(message = "Salary can not be is null")
    @NotEmpty(message = "Salary can not be is empty")
    @Digits(message = "Incorrect enter")
    @NotNegative(message = "Only numbers, can not be is negative")
    @Length(max = 9, message = "Exceeded the maximum value of Salary")
    private String employeeSalary;

    @Column(name = "employee_register")
    @NotNull(message = "Register date has incorrect value, can not be is null")
    @DateRange(format = "yyyy-mm-dd", message = "Incorrect date, should not be early than 2016-03-25")
    //@Type(type = "date")
    private Date employeeRegister;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_department")
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(String employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public Date getEmployeeRegister() {
        return employeeRegister;
    }

    public void setEmployeeRegister(Date employeeRegister) {
        this.employeeRegister = employeeRegister;
    }

}
