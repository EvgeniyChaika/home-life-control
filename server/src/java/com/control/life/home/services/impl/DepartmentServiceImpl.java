package com.control.life.home.services.impl;

import com.control.life.home.custom.exception.ValidationException;
import com.control.life.home.dao.DepartmentDAO;
import com.control.life.home.models.Department;
import com.control.life.home.services.DepartmentService;
import com.control.life.home.services.IntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created on 16.03.16.
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentDAO departmentDao;

    @Autowired
    private IntegrationService integrationService;

    @Transactional(readOnly = true)
    @Override
    public Collection findAll() {
        return departmentDao.findAll();
    }

    @Transactional()
    @Override
    public void updateDepartment(Department department) throws ValidationException {
        Department updatedDepartment = departmentDao.updateDepartment(department);
        logger.info("Department with name " + updatedDepartment.getDepartmentName() + " was saved in the database.");
//        try {
//            integrationService.createDepartmentXML(updatedDepartment);
//        } catch (JAXBException | IOException e) {
//            logger.error("Department XML file wasn't create. Department Id - " + updatedDepartment.getIdDepartment());
//            logger.error("Stack trace - " + Arrays.toString(e.getStackTrace()));
//        }
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
