package com.demo.spring.boot.bo;

/**
 * Created by bqhuy on 4/19/2018.
 */
public class Object {
    private String id;
    private String code;
    private String name;
    private String type;

    protected void createName() {
        this.name = this.id + "_" + this.code;
    }

    void print() {
        System.out.println(toString());
    }

    public String toString() {
        return new StringBuilder("Object={")
                .append("id=").append(id)
                .append(", code=").append(code)
                .append(", name=").append(name)
                .append(", type=").append(type)
                .append("}").toString();
    }
}