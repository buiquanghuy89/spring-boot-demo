package com.demo.spring.boot.entities;

import com.demo.spring.boot.entities.student.sub.Student2;

/**
 * Created by HuyBQ on 11/18/2018.
 */
public class Test {
    public static void main(String[] args) {
        Student2 student2 =new Student2();
        student2.setAge(20);
        student2.setName("Bui Quang Huy");
        //tu khoa default khi thuoc sub package cung khong duoc
//        student2.defaultVariable = "defaultVariable";
        //tu khoa protected khi thuoc sub package
//        student2.protectedVariable = "protectedVariable";
    }
}
