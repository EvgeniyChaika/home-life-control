package com.control.life.home.controllers;

import com.control.life.home.custom.exception.ValidationException;
import com.control.life.home.models.Department;
import com.control.life.home.models.Employee;
import com.control.life.home.services.DepartmentService;
import com.control.life.home.services.EmployeeService;
import com.control.life.home.utils.CustomValidator;
import com.control.life.home.utils.ModifyInputContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created on 1/17/17.
 */

@Controller
public class EmployeeController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomValidator customValidator;

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/ajaxListEmployees", method = RequestMethod.GET)
    @ResponseBody
    public Collection showAllEmployees(@RequestParam("idDepartment") Long idDepartment) {
        return employeeService.findAll(idDepartment);
    }

    @RequestMapping(value = "/ajaxRemoveEmployee", method = RequestMethod.POST)
    @ResponseBody
    public Long removeEmployee(@RequestBody Map<String, ?> params) {
        Long idEmployee = ((Number) params.get("employeeId")).longValue();
        employeeService.removeEmployee(idEmployee);
        return idEmployee;
    }

    @RequestMapping(value = "/ajaxFindByIdEmployee", method = RequestMethod.GET)
    @ResponseBody
    public Employee ajaxFindByIdEmployee(@RequestParam("employeeId") Long idDEmployee) {
        return employeeService.getEmployeeById(idDEmployee);
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxSaveNewEmployee", method = RequestMethod.POST)
    public Map<String, List<String>> saveNewEmployee(@RequestBody Employee employee, @RequestParam("idDepartment") Long depId) {
        try {
            Department department = departmentService.getDeparmentById(depId);
            employee.setDepartment(department);
            employee.setEmployeeName(ModifyInputContent.modifyEnterName(employee.getEmployeeName()));
            if (customValidator.validationObject(employee)) {
                employeeService.updateEmployee(employee);
            }
            return null;
        } catch (ValidationException e) {
            Map<String, List<String>> errorsList = new HashMap<>();
            for (Map.Entry<String, List<String>> pair : e.getErrorMap().entrySet()) {
                String key = pair.getKey();
                List<String> valueList = pair.getValue();

                if (key.equals("employeeName")) {
                    errorsList.put("errorNameEmpl", valueList);
                }
                if (key.equals("employeeEmail")) {
                    errorsList.put("errorEmailEmpl", valueList);
                }
                if (key.equals("employeeSalary")) {
                    errorsList.put("errorSalaryEmpl", valueList);
                }
                if (key.equals("employeeRegister")) {
                    errorsList.put("errorDateRegistrationEmpl", valueList);
                }
            }
            return errorsList;
        }
    }
}
