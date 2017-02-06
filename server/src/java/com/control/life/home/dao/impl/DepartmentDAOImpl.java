package com.control.life.home.dao.impl;

import com.control.life.home.dao.DepartmentDAO;
import com.control.life.home.models.Department;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created  on 15.03.16.
 */

@Service
public class DepartmentDAOImpl implements DepartmentDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Department updateDepartment(Department department) {
        Session session = sessionFactory.getCurrentSession();
        return (Department) session.merge(department);
    }

    @Override
    public Collection findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Department.class).list();
    }

    @Override
    public void removeDepartment(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Department) session.get(Department.class, id);
    }

    @Override
    public boolean getDepartmentByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Department as p where p.departmentName=?");
        Department department = (Department) query.setString(0, name).uniqueResult();
        return department != null;
    }
}