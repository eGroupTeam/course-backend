package com.example.demo.entity;

public class Product {
    private int id;
    private String name;
    private String expla;
    private int seq;
    private int price;
    private int org_id;

    public Product(int id, String name, String expla, int seq, int price, int org_id) {
        this.id = id;
        this.name = name;
        this.expla = expla;
        this.seq = seq;
        this.price = price;
        this.org_id = org_id;
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

    public String getExpla() {
        return this.expla;
    }

    public void setExpla(String expla) {
        this.expla = expla;
    }

    public int getSeq() {
        return this.seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOrg_id() {
        return this.org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
    }
}
