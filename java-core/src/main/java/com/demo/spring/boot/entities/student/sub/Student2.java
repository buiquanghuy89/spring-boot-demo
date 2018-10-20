package com.demo.spring.boot.entities.student.sub;

import com.demo.spring.boot.entities.student.Person;

/**
 * Created by HuyBQ on 10/19/2018.
 */
public class Student2 extends Person {
    public static void main(String[] args) {
        //quyen truy cap cua class con
        Student2 s = new Student2();
        //tu khoa default khi thuoc sub package cung khong duoc
//        s.tmp = "2";
        //tu khoa protected khi thuoc sub package
        s.protectedVariable = "2";

        //quyen truy cap su dung tham chieu
        Person p = new Person();
        p.setName("Huy");
    }
}
