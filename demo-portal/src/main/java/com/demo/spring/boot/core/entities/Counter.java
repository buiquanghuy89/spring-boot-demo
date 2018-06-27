package com.demo.spring.boot.core.entities;

/**
 * Created by bqhuy on 6/7/2018.
 */
public class Counter implements Calculator, Calculator2 {
    @Override
    public double calConsumption(double beforeIndex, double index) {
        double result = index - beforeIndex;
        print(result);
        return result;
    }
}
