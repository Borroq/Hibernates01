package org.example.oneToOne.model2;

import org.example.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;
import org.hibernate.query.Query;

public class UserExample {
    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.ctg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        User user = new User("Damian", "Skrzypek");
        ContactDetalis contactDetalis = new ContactDetalis("dbxcb.com", "555555555");
        //user.setContact(contactDetalis);

        contactDetalis.setUser(user);

        //session.save(contactDetalis);

        Query query = session.createQuery("FROM ContactDetalis");
        List<ContactDetalis> userList = query.list();

        userList.stream().forEach(
                u -> System.out.println(u)
        );

        session.close();
        sessionFactory.close();
    }
}
