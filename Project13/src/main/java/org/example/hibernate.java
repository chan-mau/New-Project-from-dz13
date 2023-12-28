package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernate {
    private static final SessionFactory sessionFactory;

    static{
        try{
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();

    } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed" + ex);
            throw new ExceptionInIntilizerError(ex);
        }
}
public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}