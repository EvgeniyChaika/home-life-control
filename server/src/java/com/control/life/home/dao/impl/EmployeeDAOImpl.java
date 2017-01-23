package com.control.life.home.dao.impl;

import com.control.life.home.dao.EmployeeDAO;
import com.control.life.home.models.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 15.03.16.
 */

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void updateEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(employee);
    }

    @Override
    public List<Employee> findAll(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select e from Employee as e join e.department as d where d.idDepartment=?")
                .setParameter(0, id);
        return query.list();
    }


    @Override
    public void removeEmployee(Long idEmployee) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = (Employee) session.get(Employee.class, idEmployee);
        session.delete(employee);
    }


    @Override
    public Employee getEmployeeById(Long idEmployee) {

        Session session = sessionFactory.getCurrentSession();

        return (Employee) session.get(Employee.class, idEmployee);
    }

    @Override
    public boolean getEmployeeByEmail(String email) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from Employee as p where p.employeeEmail=?");
        Employee employee = (Employee) query.setString(0, email).uniqueResult();
        return employee != null;
    }
}