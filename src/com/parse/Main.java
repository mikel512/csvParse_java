package com.parse;

import DAL.SqlAccess;
import models.SacramentoCrimeJanuary;
import models.SacramentoRealEstateTransactions;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import utilities.CsvParseBean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.module.Configuration;

public class Main {

    public static void main(String[] args) {
	// write your code here
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).addAnnotatedClass(SacramentoCrimeJanuary.class).buildMetadata().buildSessionFactory();
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/data/SacramentocrimeJanuary2006.csv"));
            CsvParseBean<SacramentoCrimeJanuary> instance = CsvParseBean.newInstance(br, SacramentoCrimeJanuary.class);
            var transactions = instance.rows();
            SqlAccess<SacramentoCrimeJanuary> access = SqlAccess.newInstance(transactions, sessionFactory);
            access.insertObjects();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
