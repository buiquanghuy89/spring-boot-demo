package com.demo.spring.boot.entities.student;

import com.demo.spring.boot.entities.Earth;
import com.demo.spring.boot.entities.student.sub.Student2;

/**
 * Created by HuyBQ on 7/10/2016.
 */
public class Test extends Earth {
    @Override
    public void toDo() {

    }

    public static void main(String[] args) {
        Person.defaultStaticVariable = "defaultStaticVariable";
        Person.protectedStaticVariable = "protectedStaticVariable";
        Person person = new Person();
        person.defaultVariable = "defaultVariable";
        person.protectedVariable = "protectedVariable";

        Student2 student2 =new Student2();
        student2.setAge(20);
        student2.setName("Bui Quang Huy");
        //tu khoa default khi thuoc sub package cung khong duoc
//        student2.defaultVariable = "defaultVariable";
        //tu khoa protected khi thuoc sub package
        student2.protectedVariable = "protectedVariable";
    }
}
