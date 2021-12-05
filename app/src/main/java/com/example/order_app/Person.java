package com.example.order_app;

public class Person {

    private int id;
    private int avatar;
    private String name;
    private String country;
    private String dis;

    public Person(int id, int avatar, String name, String country, String dis) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.country = country;
        this.dis = dis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.country = country;
    }

}
