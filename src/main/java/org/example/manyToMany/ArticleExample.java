package org.example.manyToMany;

import org.example.Employee;
import org.example.manyToMany.model.Article;
import org.example.manyToMany.model.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;
import org.hibernate.query.Query;

public class ArticleExample {
    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.ctg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Article article = new Article("JavaTutorial", "content");
        Tag tag1 = new Tag("Programming");
        article.addTag(tag1);
        Tag tag2 = new Tag("Learning");
        article.addTag(tag2);

        session.save(article);
        transaction.commit();

        Query query = session.createQuery("FROM Article");
        List<Article> articles = query.list();

        articles.stream().forEach(
                a -> {
                    System.out.println(a + ": ");
                    a.getTags().stream().forEach(t -> System.out.println(t));
                }
        );


        session.close();
        sessionFactory.close();
    }

}
