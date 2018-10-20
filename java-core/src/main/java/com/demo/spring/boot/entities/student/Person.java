package com.demo.spring.boot.entities.student;

import com.demo.spring.boot.entities.Animal;

/**
 * Created by HuyBQ on 7/10/2016.
 */
public class Person implements Animal {
    private String name;
    private int age;
    String defaultVariable;
    protected String protectedVariable;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
        A a = new A();
    }

    static String defaultStaticVariable;
    protected static String protectedStaticVariable;

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

    private class A {
        public void action() {
            Person person = new Person();
            person.age = 2;
        }
    }
}