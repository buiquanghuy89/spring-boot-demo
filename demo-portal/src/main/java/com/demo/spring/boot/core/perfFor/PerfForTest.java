package com.demo.spring.boot.core.perfFor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by HuyBQ on 9/28/2016.
 */
public class PerfForTest {
    private static List<Integer> list = new ArrayList<>();
    private static long startTime;
    private static long endTime;

    static {
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
    }

    public static void doLoop() {
        //Loại 1
        startTime = Calendar.getInstance().getTimeInMillis();
        if (list != null) {
            for (Integer i : list) {
                //
            }
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Vòng lặp for :: " + (endTime - startTime) + " ms");

        //Loại 2
        startTime = Calendar.getInstance().getTimeInMillis();
        if (list != null && list.size() > 0) {
            for (int j = 0; j < list.size(); j++) {
                Integer i = list.get(j);
            }
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Dùng collection.size() :: " + (endTime - startTime) + " ms");

        //Loại 3
        startTime = Calendar.getInstance().getTimeInMillis();
        int size = list.size();
        if (list != null && size > 0) {
            for (int j = 0; j < size; j++) {
                //System.out.println(j);
                Integer i = list.get(j);
            }
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Dùng [int size = list.size(); int j = 0; j < size ; j++] :: " + (endTime - startTime) + " ms");

        //Loại 4
        startTime = Calendar.getInstance().getTimeInMillis();
        if (list != null && list.size() > 0) {
            for (int j = list.size() - 1; j >= 0; j--) {
                //System.out.println(j);
                Integer i = list.get(j);
            }
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Dùng [int j = list.size()-1; j >=0 ; j--] :: " + (endTime - startTime) + " ms");
    }

    public static void main(String[] args) {
        int i = 0;
        while (i < 5) {
            doLoop();
            i++;
            System.out.println("-----------------------------------------------------");
        }
    }
}
