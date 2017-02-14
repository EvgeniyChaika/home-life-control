package com.control.life.home.services.batch;

import com.control.life.home.custom.exception.ValidationException;
import com.control.life.home.models.Department;
import com.control.life.home.utils.CustomValidator;
import com.control.life.home.utils.ModifyInputContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created on 06.02.17.
 */

@Component
public class DepartmentItemProcessor implements ItemProcessor<Department,Department> {

    private Logger logger = LoggerFactory.getLogger(DepartmentItemProcessor.class);

    @Autowired
    private CustomValidator customValidator;

    @Override
    public Department process(Department department) throws Exception {
        try {
            department.setDepartmentName(ModifyInputContent.modifyEnterName(department.getDepartmentName()));
            if (customValidator.validationObject(department)) {
               return department;
            }
        } catch (ValidationException e) {
            for (Map.Entry<String, List<String>> pair : e.getErrorMap().entrySet()) {
                for (String error : pair.getValue()) {
                    logger.info("Department with name " + department.getDepartmentName() + " has an error : " + error);
                }
            }
        }
        return null;
    }
}
