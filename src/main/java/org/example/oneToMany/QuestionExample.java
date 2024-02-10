package org.example.oneToMany;

import org.example.oneToMany.model.Answer;
import org.example.oneToMany.model.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class QuestionExample {
    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.ctg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Question question = new Question("Ile to 5+2");
        Answer answer1 = new Answer("8", false);
        Answer answer2 = new Answer("7", true);
        Answer answer3 = new Answer("10", false);
        Answer answer4 = new Answer("12", false);

        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        question.setAnswers(answers);
        //session.save(question);


        Question question2 = new Question("Ile to 2+2");
        List<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer("0", false));
        answers2.add(new Answer("2", false));
        answers2.add(new Answer("3", false));
        answers2.add(new Answer("4", true));
        answers2.add(new Answer("9", false));
        question2.setAnswers(answers2);
        //session.save(question2);

        // odczytanie informacji z bazy danych
        Query query = session.createQuery("FROM Question");
        List<Question> questions = query.list();
            questions.stream().forEach(
                    q -> {
                        System.out.println("\n" + q + ":");
                        q.getAnswers().stream().forEach(answer -> System.out.println("-" + answer));
                    }
            );

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
