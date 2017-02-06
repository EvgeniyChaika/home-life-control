package com.control.life.home.services.integration;

import com.control.life.home.dao.DepartmentDAO;
import com.control.life.home.models.Department;
import com.control.life.home.services.IntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created on 1/26/17.
 */

@Service
public class IntegrationServiceImpl implements IntegrationService {

    @Autowired
    private DepartmentDAO departmentDao;

    private Logger logger = LoggerFactory.getLogger(IntegrationServiceImpl.class);

    @Override
    public String createDepartmentXML(Department department) throws IOException, JAXBException {
        Marshaller marshaller = new CastorMarshaller();
        FileOutputStream os = null;
//        Department department = departmentDao.getDepartmentById(id);
        try {
            os = new FileOutputStream("department.xml");
            marshaller.marshal(department, new StreamResult(os));
        } finally {
            if (os != null) {
                os.close();
            }
        }

        logger.info("Department with id=" + department.getIdDepartment() + "XML-version created successfully");
        return null;
    }
}
