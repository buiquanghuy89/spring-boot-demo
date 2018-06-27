package com.demo.spring.boot.core.entities.student;

import com.demo.spring.boot.core.entities.Earth;

/**
 * Created by HuyBQ on 7/10/2016.
 */
public class Test extends Earth {
    @Override
    public void toDo() {

    }

    public static void main(String[] args) {
        Person.sTemp = "sTemp";
        Person.sTmp = "sTmp";
        Person person = new Person();
        person.tmp = "tmp";
        person.temp = "temp";
    }
}
