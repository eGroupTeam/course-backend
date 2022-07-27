package com.example.demo.entity;

public class Organization {
    private int id;
    private String name;
    private String set_time;
    private String desc;
    private int contactnum;
    private String mail;
    private String address;

    public Organization(int id, String name, String set_time, String desc, int contactnum, String mail,
            String address) {
        this.id = id;
        this.name = name;
        this.set_time = set_time;
        this.desc = desc;
        this.contactnum = contactnum;
        this.mail = mail;
        this.address = address;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSet_time() {
        return this.set_time;
    }

    public void setSet_time(String set_time) {
        this.set_time = set_time;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getContactnum() {
        return this.contactnum;
    }

    public void setContactnum(int contactnum) {
        this.contactnum = contactnum;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
