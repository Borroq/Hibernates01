package org.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class ProjectDAODB implements AbstractDAOInterface<Project>{
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    private SessionFactory getSessionFactory() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.ctg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    private Session openSession(){
        if(sessionFactory == null){
            sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
        }
        return session;
    }

    private Session openSessionWithTransaction(){
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    private void closeSession(){
        if(session != null){
            session.close();
        }
    }

    private void closeSessionWithTransacrion(){
        if(transaction != null){
            transaction.commit();
            closeSession();
        }
    }

    @Override
    public void persist(Project entity) {

    }

    @Override
    public void update(Project entity) {

    }

    @Override
    public Project findByID(Integer id) {
        return null;
    }

    @Override
    public void delete(Project entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Project> findAll() {
        return null;
    }
}
