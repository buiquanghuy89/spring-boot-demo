package com.demo.spring.boot.core.entities.student;

import com.demo.spring.boot.core.entities.Animal;

/**
 * Created by HuyBQ on 7/10/2016.
 */
public class Person implements Animal {
    private String name;
    private int age;
    String tmp;
    protected String temp;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    static String sTmp;
    protected static String sTemp;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void Print(String in) {
    }

    public String toString() {
        return new StringBuilder("{")
                .append("name= ").append(name)
                .append(", age= ").append(age)
                .append("}").toString();
    }
}
