package com.control.life.home.utils;

import com.control.life.home.custom.exception.ValidationException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 22.03.16.
 */

@Component
public class CustomValidator {

    @Autowired
    private Validator validator;

    public boolean validationObject(Object company) throws ValidationException {

        List<ConstraintViolation> violations = validator.validate(company);
        Map<String, List<String>> errorMap = new HashMap<>();


        for (ConstraintViolation violation : violations) {
            if (!violation.getMessage().isEmpty()) {
                List<String> list = new ArrayList<>();
                OValContext context = violation.getContext();
                Field fieldContext = ((FieldContext) context).getField();
                String field = fieldContext.getName();
                if (!field.isEmpty()) {
                    if (errorMap.containsKey(field)) {
                        for (Map.Entry<String, List<String>> pair : errorMap.entrySet()) {
                            if (field.equals(pair.getKey())) {
                                pair.getValue().add(violation.getMessage());
                            }
                        }
                    } else {
                        errorMap.put(field, list);
                        list.add(violation.getMessage());
                    }
                }

            }
        }
        if (!violations.isEmpty()) {
            throw new ValidationException(errorMap);
        }
        return true;
    }
}