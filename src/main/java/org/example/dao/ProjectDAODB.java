package org.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class ProjectDAODB implements AbstractDAOInterface<Project> {
    private SessionFactory sessionFactory = null;
    private Session session = null;
    private Transaction transaction = null;

    private SessionFactory getSessionFactory() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.ctg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    private Session openSession() {
        if (sessionFactory == null) {
            sessionFactory = getSessionFactory();
        }
        session = sessionFactory.openSession();
        return session;
    }

    private Session openSessionWithTransaction() {
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    private void closeSession() {
       if (session != null) {
            session.close();
       }
    }

    private void closeSessionWithTransacrion() {
       if (transaction != null) {
            transaction.commit();
       }
        closeSession();
    }

    @Override
    public void persist(Project entity) {
        openSessionWithTransaction().persist(entity);
        session.flush();
        closeSessionWithTransacrion();
    }

    @Override
    public void update(Project entity) {
        openSessionWithTransaction().update(entity);
        closeSessionWithTransacrion();
    }

    @Override
    public Project findByID(Integer id) {
        Project project = (Project) openSession().get(Project.class, id);
        closeSession();
        return project;
    }

    @Override
    public void delete(Project entity) {
        openSessionWithTransaction().remove(entity);
        closeSessionWithTransacrion();
    }

    @Override
    public void deleteAll() {
        List<Project> projects = findAll();
        for (Project p: projects) {
            delete(p);
        }
    }

    @Override
    public List<Project> findAll() {
        /*Session session1 = null;
        List<Project> projects = null;
        try {
           session1 = sessionFactory.openSession();
           projects = (List<Project>) session1.createQuery("FROM Project").list();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (session1 != null){
                session1.close();
            }
        }*/
        List<Project> projects = (List<Project>) openSession().createQuery("FROM Project").list();
        closeSession();
        return projects;
    }
}
