package com.demo.spring.boot.java8.date;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * Created by Admin on 1/30/2019.
 */
public class Java8LocalDateTest {
    public static void main(String[] args){
        // Get the current date and time
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Current DateTime: " + currentTime);
        System.out.println("Switch to timestamp: " + Timestamp.valueOf(currentTime));

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        LocalDate previousMonthSameDay = date1.minus(1, ChronoUnit.MONTHS);
        System.out.println("previousMonthSameDay: " + previousMonthSameDay);

        LocalDateTime beginningOfDay = date1.atStartOfDay();
        System.out.println("beginningOfDay: " + beginningOfDay);

        LocalDate firstDayOfMonth = date1.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("firstDayOfMonth: " + firstDayOfMonth);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("Month: " + month +" day: " + day +" seconds: " + seconds);
        System.out.println("date1 format: " + date1.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);
        System.out.println("date2 format: " + date2.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        //12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        //22 hour 15 minutes
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        //parse a string
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);

        LocalDate currentDate = LocalDateTime.now().toLocalDate();
        LocalDate fromDate = currentDate.minusDays(30);
        System.out.println("currentDate= " + currentDate);
        System.out.println("fromDate= " + fromDate);
        System.out.println("fromDate= " + Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }
}
