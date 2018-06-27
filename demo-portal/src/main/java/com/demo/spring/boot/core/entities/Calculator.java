package com.demo.spring.boot.core.entities;

/**
 * Created by bqhuy on 6/7/2018.
 */
public interface Calculator {
    public double calConsumption(double beforeIndex, double index);

    default void print(double input) {
        System.out.println("input: " + input);
    }
}
