package com.parse;

import com.parse.dal.SqlAccess;
import com.parse.utilities.CreateClassFileFromCsv;
import com.parse.utilities.CsvParseBean;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties = setProperties();
        assert properties != null;

        /*
            If you chose to add classes to session manually do like so:
                .addAnnotatedClass(SacramentocrimeJanuary2006.class)
                .addAnnotatedClass(SalesJan2009.class)
         */

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        final SessionFactory sessionFactory;
        MetadataSources sources= new MetadataSources(registry);
        Map<String, File> files = getData(properties);

        if(Boolean.parseBoolean(properties.getProperty("generateBeans"))) {
            createBeans(files);
        }
        if(Boolean.parseBoolean(properties.getProperty("addAnnotatedClassesToSession"))) {
            addClassesToSession(files, sources);
        }

        sessionFactory = sources.buildMetadata().buildSessionFactory();

        parseAndInsert(files, sessionFactory);
    }

    private static void createBeans(Map<String, File> files) {
        CreateClassFileFromCsv classFromCsv = new CreateClassFileFromCsv();
        // build sessionFactory
        for (Map.Entry<String, File> pair : files.entrySet()) {
            // create JavaBean
            classFromCsv.createFile(pair.getValue());
        }
    }

    private static void addClassesToSession(Map<String, File> files, MetadataSources sources) {
        for (Map.Entry<String, File> pair : files.entrySet()) {
            try {
                Class<?> clazz = Class.forName("com.parse.models." + pair.getKey());
                var a = clazz.getDeclaredConstructor().newInstance();
                sources.addAnnotatedClass(a.getClass());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void parseAndInsert(Map<String, File> files, SessionFactory sessionFactory) {
        for (Map.Entry<String, File> pair : files.entrySet()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(pair.getValue()));
                Class<?> clazz = Class.forName("com.parse.models." + pair.getKey());
                CsvParseBean<?> parseBean = CsvParseBean.newInstance(br, clazz);
                var rows = parseBean.rows();
                SqlAccess<?> sqlAccess = SqlAccess.newInstance(rows, sessionFactory);
                sqlAccess.insertObjects();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Map<String, File> getData(Properties properties) {
        Map<String, File> result = new HashMap<>();
        File dir = new File(properties.getProperty("dataPath"));
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".csv"));
        assert files != null;
        for(File csv : files) {
            String name = csv.getName().substring(0, csv.getName().lastIndexOf('.'));
            result.put(name, csv);
        }
        return result;
    }

    private static Properties setProperties() {
        Properties props = new Properties();
        try {
            props.loadFromXML(new FileInputStream("settings.xml"));
            return props;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
