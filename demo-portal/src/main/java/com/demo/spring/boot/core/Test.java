package com.demo.spring.boot.core;

import com.demo.spring.boot.core.entities.student.Person;
import com.demo.spring.boot.core.entities.student.Student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by HuyBQ on 7/10/2016.
 */
public class Test {
    public static void main(String[] args) {
        String[][] argCopy = new String[2][2];
        argCopy[0] = new String[]{"1", "2", "3"};
        int x;
        args = new String[]{"1", "2", "3"};
        argCopy[0] = args;
        x = argCopy[0].length;
        for (int y = 0; y < x; y++) {
            System.out.print(argCopy[0][y]);
        }
        int a = 1;
        System.out.println();
        System.out.println(a++);
        System.out.println(++a);
        System.out.println((8 >> 2));
        System.out.println((8 >> 2) << 4);
        System.out.println(-128 >> 2);
        System.out.println(-128 >>> 2);
        System.out.println(2 ^ 5);

        Person p = new Person();

        String s = "0123456";
        System.out.println(s.substring(0, 2));
        System.out.println('a' + 1);

        int i = 0;
        int j = 0;
        boolean t = true;
        boolean r;
        r = (t & 0 < (i += 1));
        System.out.println(r + " " + i);
        r = (t && 0 < (i += 2));
        System.out.println(r + " " + i);
        r = (t | 0 < (j += 1));
        System.out.println(r + " " + j);
        r = (t || 0 < (j += 2));
        System.out.println(r + " " + j);
        System.out.println(i + " " + j);

        int n = 8;
        int m = 3;
        float z = (float) n++ / m--;
        System.out.println(z);

        for (i = 0; i < 4; i++) ;
        short sh = 7;
        int ia = sh;

        for (i = 5; i < 10; i++) {
            System.out.format("|");
            for (j = 0; j < 3; j++) {
                String format = "%" + i + "." + j + "f|";
                System.out.print(format);
                System.out.format(format, 123.456);
            }
            System.out.format("%n");
        }

        Student student = new Student("Huy", 25, "transient");
        System.out.println(student.toString());
        try {
            FileOutputStream fos = new FileOutputStream("student.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(student);
            oos.close();
            fos.close();

            FileInputStream fis = new FileInputStream("student.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            student = (Student) ois.readObject();
            System.out.println(student.toString());
            oos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

//        List<String> lst = new ArrayList<>();
//        lst.add("B");
//        lst.add("A");
//        lst.add("1");
//        lst.add("2");
//        lst.add("0  ");
//        lst.add("C ");
//        System.out.println(StringUtils.join(lst, "->") + ".");
//        System.out.println(StringUtils.trimWhitespace(StringUtils.join(lst, "->")) + ".");

        int ar2[][][] = {{{1, 2, 3}, {2, 2, 3}, {3, 2}}};

        System.out.println(convertTo8BitBinary(-5));

        Vector<Object> vtSend = new Vector<Object>();
        Vector<Object> vtReturn = new Vector<Object>();
        vtReturn.add("1");
        vtReturn.add("Ha noi");
        vtReturn.add("Vietnam");
        vtReturn.add(true);
        vtReturn.add("2");
        vtSend.add(vtReturn);
        System.out.println(vtSend.toString());
        try {
            FileOutputStream fos = new FileOutputStream("student.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vtSend);
            oos.close();
            fos.close();

            FileInputStream fis = new FileInputStream("student.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Vector<Object> vt = (Vector<Object>) ois.readObject();
            System.out.println(vt.toString());
            oos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            Calendar cal = Calendar.getInstance();
            Date fromDate = sdf.parse("20170506000000");
            cal.setTime(fromDate);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);
            long bucketTs = year * 10000 + month * 100 + day;
            System.out.println("xxx: " + bucketTs);
            Date toDate = sdf.parse("20170601000000");
            SimpleDateFormat _sdf = new SimpleDateFormat("yyyyMMdd");
            _sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            long lStartDate = Long.parseLong(_sdf.format(fromDate));
            long lEndDate = Long.parseLong(_sdf.format(toDate));
            while (lStartDate <= lEndDate) {
                System.out.println(lStartDate);
                cal.add(Calendar.DATE, 1);
                lStartDate = Long.parseLong(_sdf.format(cal.getTime()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<Person> persons = new ArrayList<>();
//        persons.add(new Person(null));
        persons.add(new Person("20170101"));
        persons.add(new Person("20170201"));
        persons.add(new Person("20170104"));
        persons.add(new Person(null));
        persons.add(new Person("20171101"));
        persons.add(new Person("20170101"));
        persons.add(new Person("20170102"));

//        orderByCreateDate(persons);

        //java 8
        //lambda
        System.out.println("lambda with Consumer");
        persons.forEach((person) -> System.out.println(person.getName()));
        System.out.println("lambda with Predicate");
        persons.stream().filter(person -> person.getName() != null).forEach(person -> System.out.println(person.getName()));
        System.out.println("lambda with Collect");
        Map<String, List<Person>> map = persons.stream().collect(Collectors.groupingBy(person -> person.getName() != null ? person.getName() : "null"));
        System.out.println(map);

        Collections.sort(persons, (lhs, rhs) -> {
            String strCreateDateLhs = "";
            String strCreateDateRhs = "";
            try {
                strCreateDateLhs = lhs.getName();
                strCreateDateRhs = rhs.getName();

                if (strCreateDateLhs == null && strCreateDateRhs == null) {
                    System.out.println(0);
                    return 0;
                } else if (strCreateDateLhs == null) {
//                    System.out.println(1 + "," + strCreateDateRhs);
                    return 1;
                } else if (strCreateDateRhs == null) {
//                    System.out.println(-1 + "," + strCreateDateLhs);
                    return -1;
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    Date createDateLhs = sdf.parse(strCreateDateLhs);
                    Date createDateRhs = sdf.parse(strCreateDateRhs);
                    return createDateRhs.compareTo(createDateLhs);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        });
        System.out.println(persons);
        BigDecimal d1 = new BigDecimal("1.14");
        System.out.println((d1.subtract(new BigDecimal("1"))).multiply(new BigDecimal("100")));

        int foo = 1;
    }

    public static String convertTo8BitBinary(int myNum) {
        String intToConv = Integer.toBinaryString(myNum);
        //the number is less than 8-bits
        if (intToConv.length() < 8) {
            String append = "";
            for (int i = 8 - intToConv.length(); i > 0; i--) {
                append += "0";
            }
            intToConv = append + intToConv;
            //the number is more than 8 bits
        } else {
            intToConv = intToConv.substring(intToConv.length() - 8, intToConv.length());
        }
        return intToConv;
    }

    private static void orderByCreateDate(List<Person> lst) {
        Collections.sort(lst, new Comparator<Person>() {
            @Override
            public int compare(Person lhs, Person rhs) {
                String strCreateDateLhs = "";
                String strCreateDateRhs = "";
                try {
                    strCreateDateLhs = lhs.getName();
                    strCreateDateRhs = rhs.getName();

                    if (strCreateDateLhs == null && strCreateDateRhs == null) {
                        System.out.println(0);
                        return 0;
                    } else if (strCreateDateLhs == null) {
                        System.out.println(1 + "," + strCreateDateRhs);
                        return 1;
                    } else if (strCreateDateRhs == null) {
                        System.out.println(-1 + "," + strCreateDateLhs);
                        return -1;
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        Date createDateLhs = sdf.parse(strCreateDateLhs);
                        Date createDateRhs = sdf.parse(strCreateDateRhs);
                        return createDateRhs.compareTo(createDateLhs);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return 0;
            }
        });
    }
}

