package com.parse.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class HibernateUtility {
    private static Set<String> classNames;

    private HibernateUtility(Set<String> classes) {
        this.classNames = classes;
    }

    public static HibernateUtility newInstance(Set<String> classes) {
        return new HibernateUtility(classes);
    }

    public SessionFactory getSessionFactory() {
        return setUp();
    }


    private static synchronized SessionFactory setUp() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        MetadataSources sources= new MetadataSources(registry);
        for (String name : classNames) {
            try {
                Class<?> clazz = Class.forName("com.parse.models._" + name);
                var a = clazz.getDeclaredConstructor().newInstance();
                sources.addAnnotatedClass(a.getClass());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sources.buildMetadata().buildSessionFactory();
    }
}
