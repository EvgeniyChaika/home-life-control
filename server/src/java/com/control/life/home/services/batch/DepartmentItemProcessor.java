package com.control.life.home.services.batch;

import com.control.life.home.models.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Created on 06.02.17.
 */

@Component
public class DepartmentItemProcessor implements ItemProcessor<Department,Department> {

    private Logger logger = LoggerFactory.getLogger(DepartmentItemProcessor.class);

    @Override
    public Department process(Department department) throws Exception {
        String name = department.getDepartmentName().toUpperCase();
        department.setDepartmentName(name);
        logger.info("Department with id=" + department.getIdDepartment()
                + " and name-" + department.getDepartmentName() + " was transformed to UpperCase");
        return department;
    }
}
