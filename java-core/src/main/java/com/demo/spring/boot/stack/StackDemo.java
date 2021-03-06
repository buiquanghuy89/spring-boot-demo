package com.demo.spring.boot.stack;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by HuyBQ on 9/28/2016.
 */
public class StackDemo {
    static void showpush(Stack st, int a) {
        st.push(new Integer(a));
        System.out.println("push(" + a + ")");
        System.out.println("stack: " + st);
    }
    static void showpop(Stack st) {
        System.out.print("pop -> ");
        Integer a = (Integer) st.pop();
        System.out.println(a);
        System.out.println("stack: " + st);
    }
    public static void main(String args[]) {
        Stack st = new Stack();
        System.out.println("stack: " + st);

        showpush(st, 42);
        showpush(st, 66);
        showpush(st, 99);

        System.out.println("Index of element="+st.search(66));

        showpop(st);
        showpop(st);
        showpop(st);

        try {
            showpop(st);
        } catch (EmptyStackException e) {
            System.out.println("empty stack");
        }
    }
}
