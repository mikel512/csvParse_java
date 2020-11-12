package com.parse.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.stream.Stream;

public class SqlAccess<T> {
    private final SessionFactory sessionFactory;
    private final Stream<T> objectStream;

    private SqlAccess (Stream<T> objectStream,SessionFactory sessionFactory) {
        this.objectStream = objectStream;
        this.sessionFactory = sessionFactory;
    }

    /**
     * Creates a SqlAccess instance
     * @param stream a {@link Stream} containing {@code n} objects of type {@code T}.
     * @param <T> the type corresponding to the stream objects
     * @return a new SqlAccess instance
     */
    public static <T> SqlAccess<T> newInstance (Stream<T> stream, SessionFactory sessionFactory) {
        return new SqlAccess<>(stream, sessionFactory);
    }


    /**
     * Inserts each object in a {@link Stream} to its corresponding table using Hibernate ORM
     */
    public void insertObjects() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        objectStream.forEach(session::save);
        session.getTransaction().commit();
        session.close();

    }

}
