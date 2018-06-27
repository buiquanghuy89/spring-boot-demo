package com.demo.spring.boot.core.algorithm;

import com.demo.spring.boot.core.entities.student.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by HuyBQ on 9/29/2016.
 */
public class demo {
    private int a;

    public static void main(String[] args) {
        //Doi cho 2 so
        int a = 3;
        int b = 5;
        a += b;
        b = a - b;
        a = a - b;
        System.out.println(a);
        System.out.println(b);

        try {
            int x = Integer.parseInt("a");
        } catch (Exception ex) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }

        Set s = new HashSet<String>();
        s.add("a");
        s.add("a");
        System.out.println(s);
        Set s1 = new HashSet<Student>();
        Student student1 = new Student("a", 1, "");
        Student student2 = new Student("a", 1, "");
        s1.add(student1);
        s1.add(student2);
        s1.add(student1);
        System.out.println(s1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            System.out.println(sdf.parse("2017-04-09T17:00:00.000Z".replaceAll("Z$", "+0000")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<String> lst = new ArrayList<>();
//        lst.add("1");
//        lst.add("2");
//        lst.add("3");
//        lst.add("4");
        lst.add("2");
        for (int i = 0; i < lst.size(); i++) {
            String item = lst.get(i);
            if ("2".equals(item)) {
                lst.remove(i);
            }
        }
        System.out.println(lst);
        Iterator<String> it = lst.iterator();
        while (it.hasNext()) {
            String item = it.next();
            if ("2".equals(item)) {
                it.remove();
            }
        }
        System.out.println(lst);

        List<String> lst2 = new ArrayList<>();
        lst2.add("NC > N'B");
        lst2.add("NC < N'B");
        lst2.add("dépassement de consommation");
        lst2.add("consommation négative");
        lst2.add("niveau de stock");
        lst2.add("Rendement installation (contrat)");
        lst2.add("Rendement installation (budget)");
        lst2.add("Ecart qECS contractuel");
        lst2.add("Ecart qECS budget");
        for (String info : lst2) {
            System.out.println(info.hashCode());
        }
        System.out.println(lst2.contains("NC > N'B"));
        System.out.println(lst2.contains("NC > N'"));
    }
}
