package com.example.demo.entity;

public class Organization {
    private int id;
    private String date;
    private String name;
    private String intro;
    private String phone;
    private String email;
    private String address;

    public Organization(int id, String date, String name, String intro, String phone, String email, String address) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.intro = intro;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
