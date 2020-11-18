package com.parse;

import com.parse.dal.SqlAccess;
import com.parse.utilities.CreateClassFileFromCsv;
import com.parse.utilities.CsvParseBean;
import com.parse.utilities.HibernateUtility;
import org.hibernate.SessionFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties = setProperties();
        assert properties != null;

        Map<String, File> files = getData(properties.getProperty("dataPath"));

        if(Boolean.parseBoolean(properties.getProperty("generateBeans"))) {
            CreateClassFileFromCsv fileFromCsv = CreateClassFileFromCsv.newInstance();
            fileFromCsv.createAll(files);
        }
        HibernateUtility hibernate = HibernateUtility.newInstance(files.keySet());
        SessionFactory sessionFactory = hibernate.getSessionFactory();

        parseAndInsert(files, sessionFactory);
    }

    static void parseAndInsert(Map<String, File> files, SessionFactory sessionFactory) {
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

    static Map<String, File> getData(String dirName) {
        Map<String, File> files = new HashMap<>();
        getDataHelper(dirName, files);
        return files;
    }

    static void getDataHelper(String dirName, Map<String, File>  fileMap) {
        File dir = new File(dirName);
        File[] files = dir.listFiles();
        assert files != null;
        for(File file : files) {
            if(file.isFile() && file.getName().endsWith(".csv")) {
                String name = file.getName().substring(0, file.getName().lastIndexOf('.'));
                fileMap.put(name, file);
            }
            else if (file.isDirectory()) {
                getDataHelper(file.getAbsolutePath(), fileMap);
            }
        }
    }

    static Properties setProperties() {
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
