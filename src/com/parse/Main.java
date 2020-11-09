package com.parse;

import com.parse.controllers.MainController;
import com.parse.utilities.SacramentoCrimeJanuary2006;
import com.parse.models.Sacramentorealestatetransactions;
import com.parse.models.SalesJan2009;
import com.parse.models.TechCrunchcontinentalUSA;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

    // TODO rework project structure, move Services???
    public static void main(String[] args) {
        //
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        final SessionFactory sessionFactory = new MetadataSources(registry)
                .addAnnotatedClass(SacramentoCrimeJanuary2006.class)
                .addAnnotatedClass(SalesJan2009.class)
                .addAnnotatedClass(Sacramentorealestatetransactions.class)
                .addAnnotatedClass(TechCrunchcontinentalUSA.class)
                .buildMetadata()
                .buildSessionFactory();
        MainController controller = new MainController(sessionFactory);
        controller.ParseAndInsertSacCrimeJanuary();
    }
}
