package com.demo.spring.boot.entities.student;

import com.demo.spring.boot.entities.Earth;

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
    }
}
