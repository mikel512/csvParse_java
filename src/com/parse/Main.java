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
                .configure()
                .build();
        SessionFactory sessionFactory;
        MetadataSources sources= new MetadataSources(registry);
        Map<String, File> files = new HashMap<>();
        getData(properties.getProperty("dataPath"), files);

        if(Boolean.parseBoolean(properties.getProperty("generateBeans"))) {
            //createBeans(files);
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
                Class<?> clazz = Class.forName("com.parse.models._" + pair.getKey());
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
                Class<?> clazz = Class.forName("com.parse.models._" + pair.getKey());
                CsvParseBean<?> parseBean = CsvParseBean.newInstance(br, clazz);
                var rows = parseBean.rows();
/*
                rows.forEach(i -> System.out.println(i));
*/
                SqlAccess<?> sqlAccess = SqlAccess.newInstance(rows, sessionFactory);
                sqlAccess.insertObjects();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void getData(String dirName, Map<String, File>  fileMap) {
        File dir = new File(dirName);
        File[] files = dir.listFiles();
        assert files != null;
        for(File file : files) {
            if(file.isFile() && file.getName().endsWith(".csv")) {
                String name = file.getName().substring(0, file.getName().lastIndexOf('.'));
                fileMap.put(name, file);
            }
            else if (file.isDirectory()) {
                getData(file.getAbsolutePath(), fileMap);
            }
        }
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
