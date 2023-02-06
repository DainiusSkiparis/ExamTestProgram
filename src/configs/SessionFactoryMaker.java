package configs;

import entities.Answer;
import entities.Exam;
import entities.Question;
import entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryMaker {
    private static SessionFactory factory;

    private static void configureFactory() {
        try {
            factory = new Configuration()
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Exam.class)
                    .addAnnotatedClass(Question.class)
                    .addAnnotatedClass(Answer.class)
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getFactory() {
        if (factory == null) {
            configureFactory();
        }

        return factory;
    }
}

