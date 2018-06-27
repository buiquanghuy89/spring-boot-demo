package com.demo.spring.boot.core.entities.student;

import java.io.Serializable;

/**
 * Created by HuyBQ on 7/10/2016.
 */
public class Student extends Person implements Serializable{
    private int mark;
    private transient String _transient;

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public static void main(String[] args) {
        Student s = new Student();
        s.setMark(1);
        s.setAge(20);
        s.setName("Bui Quang Huy");
        s.tmp = "tmp";

        String str = "str";
        try {
            System.out.println(Integer.parseInt(str));
        } catch (Exception ex) {
            System.out.println("catch");
            //throw ex;
        } finally {
            System.out.println("finally");
        }

        short sh = 127;
        byte bt = 127;
        System.out.println(sh);
        System.out.println(bt);

        char a = '@';//tương ứng với giá trị 64 trong ascii
        int i = a + 1;//char có thể tham gia phép tính kiểu int
        System.out.println(i);

        float fl=3.45F;
    }

    public String get_transient() {
        return _transient;
    }

    public void set_transient(String _transient) {
        this._transient = _transient;
    }

    public Student(String name, int age, String _transient) {
        setName(name);
        setAge(age);
        this._transient = _transient;
    }

    public Student(){}

    public String toString(){
        return new StringBuilder().append("Name=").append(getName())
                .append(", Age=").append(getAge())
                .append(", Transient=").append(this._transient).toString();
    }

    public void print(){
        System.out.println(toString());
    }
}
