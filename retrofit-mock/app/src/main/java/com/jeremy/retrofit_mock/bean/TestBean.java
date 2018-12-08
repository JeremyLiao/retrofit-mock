package com.jeremy.retrofit_mock.bean;

/**
 * Created by liaohailiang on 2018/11/1.
 */
public class TestBean {

    private String name;
    private int id;

    public TestBean() {
    }

    public TestBean(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "name: " + name + ";" + "id: " + id;
    }
}
