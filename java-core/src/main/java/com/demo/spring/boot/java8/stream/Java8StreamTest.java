package com.demo.spring.boot.java8.stream;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Admin on 2/20/2019.
 */
public class Java8StreamTest {
    public static void main(String[] args) {
        List<Item> lst = new ArrayList<>();
        lst.add(new Item(1, "a"));
        lst.add(new Item(2, "b"));
        lst.add(new Item(3, "c"));
        lst.add(new Item(4, "a"));
        lst.add(new Item(5, "d"));
        lst.add(new Item(6, "e"));

        System.out.println("data: " + lst);
        System.out.println("Get number item of list");
        System.out.println("Number= " + lst.stream().distinct().count());

        System.out.println("For list by number core of CPU");
        lst.parallelStream().forEach(item -> {
            try {
                Thread.sleep(2000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(new Date().getTime() + "-> item= " + item);
        });

        System.out.println("Check item a from list");
        System.out.println("Item a is exist (anyMatch): " + lst.stream().anyMatch(item -> item.getName().contains("a")));
        System.out.println("Item a is exist (noneMatch): " + lst.stream().noneMatch(item -> item.getName().contains("a")));
        System.out.println("Item a is exist (allMatch): " + lst.stream().allMatch(item -> item.getName().contains("a")));

        System.out.println("Get item name a from list");
        System.out.println("Info of item name a: " + lst.stream().filter(item -> item.getName().contains("a")).collect(Collectors.toList()));

        System.out.println("List Item to list String");
        System.out.println("List String from list (use map): " + lst.stream().map(item -> item.getName().toUpperCase()).collect(Collectors.toList()));
        System.out.println("List String from list (use flatMap when list of list)");

        System.out.println("Sum of id: " + lst.stream().map(item -> item.getId()).collect(Collectors.toList()).stream().reduce(0, (a, b) -> (a + b)));

        System.out.println("List: " + lst);
        lst.stream().forEach(obj -> obj.setName("a"));
        System.out.println("List: "+ lst);

        Stream<Integer> streamByGenerated = Stream.generate(() -> 40).limit(10);
        System.out.println("Auto gen list (generate): " + streamByGenerated.collect(Collectors.toList()));

        Stream<Integer> streamByIterated = Stream.iterate(40, n -> n + 2).limit(10);
        System.out.println("Auto gen list (iterate): " + streamByIterated.collect(Collectors.toList()));

        IntStream intStreamByRange = IntStream.range(1, 3);
        System.out.println("Auto gen list (IntStream.range): " + intStreamByRange.boxed().collect(Collectors.toList()));
        IntStream intStreamByRangeClosed = IntStream.rangeClosed(1, 3);
        System.out.println("Auto gen list (IntStream.rangeClosed): " + intStreamByRangeClosed.boxed().collect(Collectors.toList()));

        String s = "a, b, c";
        Stream<String> streamOfString = Pattern.compile(",").splitAsStream(s);
        System.out.println("Split \"" + s + "\" to list:" + streamOfString.map(item -> item.trim()).collect(Collectors.toList()));
        int x = 0;
    }

    static class Item {
        private Integer id;
        private String name;

        public Item(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public String toString() {
            return new Gson().toJson(this);
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
