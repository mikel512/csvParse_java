package com.parse.controllers;

import com.parse.utilities.CreateClassFromCsv;
import org.hibernate.SessionFactory;

import java.io.BufferedReader;
import java.io.FileReader;

public class MainController {
    SessionFactory sessionFactory;

    public MainController(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void ParseAndInsertSacCrimeJanuary() {
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/data/SacramentocrimeJanuary2006.csv"));

            CreateClassFromCsv classFromCsv = new CreateClassFromCsv().withAnnotations();
            classFromCsv.createFile("SacramentocrimeJanuary2006.csv", br.readLine());
/*
            CsvParseBean<SacramentoCrimeJanuary2006> instance = CsvParseBean.newInstance(br, SacramentoCrimeJanuary2006.class);
            var transactions = instance.rows();
            SqlAccess<SacramentoCrimeJanuary2006> access = SqlAccess.newInstance(transactions, sessionFactory);
            access.insertObjects();
*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
