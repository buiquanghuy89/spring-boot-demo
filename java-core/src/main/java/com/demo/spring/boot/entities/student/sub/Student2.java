package com.demo.spring.boot.entities.student.sub;

import com.demo.spring.boot.entities.student.Person;

/**
 * Created by HuyBQ on 10/19/2018.
 */
public class Student2 extends Person {
    public static void main(String[] args) {
        //quyen truy cap cua class con
        Student2 s = new Student2();
        s.setAge(20);
        s.setName("Bui Quang Huy");
        //tu khoa default khi thuoc sub package cung khong duoc
//        s.defaultVariable = "defaultVariable";
        //tu khoa protected khi thuoc sub package
        s.protectedVariable = "protectedVariable";

        //quyen truy cap su dung tham chieu
        Person p = new Person();
        p.setAge(20);
        p.setName("Bui Quang Huy");
        //Khong the truy cap do khong ke thua
//        p.defaultVariable = "";
//        p.protectedVariable = "";
        Person.protectedStaticVariable = null;
        //Khong the truy cap do khac package
//        Person.defaultStaticVariable = null;
    }
}
