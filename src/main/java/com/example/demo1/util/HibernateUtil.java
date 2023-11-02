package com.example.demo1.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/9/19 9:30
 * @version: 1.0
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex){
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
