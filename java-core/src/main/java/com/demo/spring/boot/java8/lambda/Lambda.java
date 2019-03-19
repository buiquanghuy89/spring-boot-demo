package com.demo.spring.boot.java8.lambda;

import com.demo.spring.boot.java8.fuctionInterface.ICalculator;
import com.demo.spring.boot.java8.fuctionInterface.MathUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HuyBQ on 3/19/2019.
 */
public class Lambda {
    public static void main(String[] args) {
        List<String> lst = new ArrayList<>();
        lst.add("C");
        lst.add("A");
        lst.add("B");
        lst.add("1");

        System.out.println("List: " + lst);
        Comparator<String> stringSort = (o1, o2) -> o1.compareTo(o2);
        lst.sort(stringSort);
        System.out.println("Sorted list: " + lst);
        lst.sort(stringSort.reversed());
        System.out.println("Reversed list: " + lst);

        List<Human> lstHuman = new ArrayList<>();
        lstHuman.add(new Human("A", 12));
        lstHuman.add(new Human("E", 1));
        lstHuman.add(new Human("A", 20));
        System.out.println("lstHuman: " + lstHuman);
        lstHuman.sort(Comparator.comparing(Human::getName).thenComparing(Human::getAge));
        System.out.println("Sorted lstHuman: " + lstHuman);
        lstHuman = lstHuman.stream().sorted(Comparator.comparing(Human::getAge)).collect(Collectors.toList());
        System.out.println("Sorted lstHuman (by stream.sorted): " + lstHuman);

        ICalculator addFunction = (o1, o2) -> o1 + o2;
        int a = 1;
        int b = 2;
        System.out.println("Use function interface " + a + " + " + b + " = " + addFunction.doSomething(a, b));
        System.out.println("Use method reference " + a + " + " + b + " = " + calculate(a,b, MathUtils::sum));
        System.out.println("Use method reference " + a + " - " + b + " = " + calculate(a,b, MathUtils::sub));
    }

    private static int compareByString(String lhs, String rhs) {
        return lhs.compareTo(rhs);
    }

    private static long calculate(int a, int b, ICalculator calculator) {
        return calculator.doSomething(a, b);
    }
}

class Human {
    private String name;
    private int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}