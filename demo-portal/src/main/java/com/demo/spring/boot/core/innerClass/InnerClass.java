package com.demo.spring.boot.core.innerClass;

/**
 * Created by HuyBQ on 9/28/2016.
 */
public class InnerClass {
    private String x;

    public String getX() {
        return this.x;
    }

    public InnerClass() {
    }

    public InnerClass(String x) {
        this.x = x;
    }

    //Lop noi trong class
    class Inner {
        public void print(String y) {
            System.out.println("InnerClass trong Class, " + y + x);
        }
    }

    public void print(String y) {
        String n = "es";
        final String m = "s";
        //Lop noi trong phuong thuc
        class _Inner {
            public void print(String y) {
                System.out.println("InnerClass trong phuong thuc, " + y + x + m);
            }
        }
        _Inner _inner = new _Inner();
        _inner.print(y);
    }

    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass("stuff");
        Inner inner = innerClass.new Inner();
        inner.print("do");
        innerClass.print("do");

        InnerClass _innerClass = new InnerClass("stuff") {
            public void print(String y) {
                System.out.println("InnerClass vo danh, override method print, " + y + getX());
            }
        };
        _innerClass.print("do");
    }
}
