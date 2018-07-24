package com.demo.spring.boot;

/**
 * Created by bqhuy on 7/12/2018.
 */
public class StringUtils {
    public static void print(String input){
        System.out.println(input);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }
}
