package com.control.life.home.controllers;

import com.control.life.home.custom.exception.ValidationException;
import com.control.life.home.models.Department;
import com.control.life.home.services.DepartmentService;
import com.control.life.home.utils.CustomValidator;
import com.control.life.home.utils.ModifyInputContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created on 1/17/17.
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CustomValidator customValidator;

    @RequestMapping("/ajaxListDepartments")
    @ResponseBody
    public Collection ajaxListDepartments() {
        ArrayList<Department> list = new ArrayList();
        Department department = new Department();
        department.setIdDepartment(1L);
        department.setDepartmentName("Test");
        department.setEmployeeList(null);
        list.add(department);
        return list;
//        return departmentService.findAll();

    }

    @RequestMapping(value = "/ajaxFindByIdDepartment", method = RequestMethod.GET)
    @ResponseBody
    public Department ajaxFindByIdDepartment(@RequestParam("idDepartment") Long idDepartment) {
        return departmentService.getDeparmentById(idDepartment);
    }

    @RequestMapping(value = "/ajaxRemoveDepartment", method = RequestMethod.POST)
    @ResponseBody
    public Long removeDepartment(@RequestBody Map<String, ?> params) {
        Long idDepartment = ((Number) params.get("idDepartment")).longValue();
        departmentService.removeDepartment(idDepartment);
        return idDepartment;
    }

    @RequestMapping(value = "/ajaxSaveNewDepartment", method = RequestMethod.POST)
    @ResponseBody
    public List<String> saveNewDepartment(@RequestBody Department department) throws SQLException {
        List<String> valueList = null;
        try {
            department.setDepartmentName(ModifyInputContent.modifyEnterName(department.getDepartmentName()));
            if (customValidator.validationObject(department)) {
                departmentService.updateDepartment(department);
            }
        } catch (ValidationException e) {
            for (Map.Entry<String, List<String>> pair : e.getErrorMap().entrySet()) {
                valueList = pair.getValue();
            }
        }
        return valueList;
    }

//    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
//    public void viewEdit(@PathVariable("name") final String name) {
//        if (name.equals("null")) throw new ResourceNotFoundException();
//    }
}
