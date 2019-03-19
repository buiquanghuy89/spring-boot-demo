package com.demo.spring.boot.java8.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by Admin on 1/30/2019.
 */
public class Java8ChromoUnitsTest {
    public static void main(String[] args) {
        //Get the current date
        LocalDate today = LocalDate.now();
        System.out.println("Current date: " + today);

        //add 1 week to the current date
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Next week: " + nextWeek);

        //add 1 month to the current date
        LocalDate nextMonth = today.plus(1, ChronoUnit.MONTHS);
        System.out.println("Next month: " + nextMonth);

        //add 1 year to the current date
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("Next year: " + nextYear);

        //add 10 years to the current date
        LocalDate nextDecade = today.plus(1, ChronoUnit.DECADES);
        System.out.println("Date after ten year: " + nextDecade);

        LocalDate currentDate = LocalDateTime.now().toLocalDate();
        LocalDate fromDate = currentDate.minusDays(364);
        System.out.println("currentDate= " + currentDate);
        System.out.println("Get Agents don't do anything util fromDate= " + fromDate);
        System.out.println(String.format("Auto suspended because user is inactive after %d days",30));
    }
}
