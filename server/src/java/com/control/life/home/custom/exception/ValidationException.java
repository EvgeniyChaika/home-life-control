package com.control.life.home.custom.exception;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 23.03.16.
 */

@Component
public class ValidationException  extends Exception{

    private Map<String, List<String>> errorMap = new HashMap<>();


    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Map<String,List<String>> errorMap){
        this.errorMap = errorMap;
    }


    public Map<String,List<String>> getErrorMap(){
        return errorMap;
    }
}
