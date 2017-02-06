package com.control.life.home.services;

import com.control.life.home.models.Department;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created on 1/26/17.
 */
public interface IntegrationService {

    String createDepartmentXML(Department department) throws JAXBException, IOException;
}
