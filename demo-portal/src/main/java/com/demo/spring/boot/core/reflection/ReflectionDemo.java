package com.demo.spring.boot.core.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by HuyBQ on 9/27/2016.
 */
public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        String className = "com.demo.spring.boot.core.entities.student.Student";
        Class<?> aClass = Class.forName(className); // convert string classname to class
        Object student = aClass.newInstance(); // invoke empty constructor

        String methodName = "";

        // with single parameter, return void
        methodName = "setName";
        Method setNameMethod = student.getClass().getMethod(methodName, String.class);
        setNameMethod.invoke(student, "Mishka"); // pass arg

        // without parameters, return string
        methodName = "getName";
        Method getNameMethod = student.getClass().getMethod(methodName);
        String name = (String) getNameMethod.invoke(student); // explicit cast
        System.out.println("name: " + name);

        // with single parameter, return void
        methodName = "setAge";
        Method setAgeMethod = student.getClass().getMethod(methodName, int.class);
        setAgeMethod.invoke(student, 5); // pass arg

        // without parameters, return string
        methodName = "getAge";
        Method getAgeMethod = student.getClass().getMethod(methodName);
        int age = (int) getAgeMethod.invoke(student); // explicit cast
        System.out.println("age: " + age);

        // with multiple parameters
        methodName = "print";
        Class<?>[] paramTypes = {};
        Method printDogMethod = student.getClass().getMethod(methodName, paramTypes);
        printDogMethod.invoke(student); // pass args

        Constructor<?> aConstructor = aClass.getConstructor(String.class, int.class, String.class);
        Object student2 = aConstructor.newInstance("Hachiko", 10, "a");
        printDogMethod.invoke(student2);
    }
}
