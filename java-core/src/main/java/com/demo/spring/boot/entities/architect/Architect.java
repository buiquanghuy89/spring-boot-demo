package com.demo.spring.boot.entities.architect;

import com.demo.spring.boot.entities.student.Person;

/**
 * Created by HuyBQ on 7/10/2016.
 */
public class Architect extends Person {
    public static void main(String[] args) {
        Architect a = new Architect();
        a.protectedVariable = "protectedVariable";
        Person.protectedStaticVariable = "sTemp";
    }
}
