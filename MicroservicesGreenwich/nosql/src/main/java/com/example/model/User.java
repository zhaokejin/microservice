package com.example.model;

import java.io.Serializable;

public class User implements Serializable {


    private String name;

    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
}
