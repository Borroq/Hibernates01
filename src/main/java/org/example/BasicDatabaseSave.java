package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BasicDatabaseSave {
    public static void main(String[] args) {

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.ctg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Employee employee = new Employee();
        //employee.setId(1); //zmieniono na increment w employee.hbm.xml
        employee.setName("Damian");
        employee.setSurname("Skrzypek");
        employee.setJobTitle("Programmer");
        employee.setSalary(18000);

        session.save(employee);
        transaction.commit();

        System.out.println("Employee saved");

        session.close();
        sessionFactory.close();


    }
}
