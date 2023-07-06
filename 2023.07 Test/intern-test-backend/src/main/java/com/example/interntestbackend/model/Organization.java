package com.example.interntestbackend.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "organization_name")
    private String name;

    @Column(name = "organization_description")
    private String description;

    @Column(name = "organization_date")
    private Date date;

    @Column(name = "organization_tel")
    private String tel;

    @Column(name = "organization_mail")
    private String mail;

    @Column(name = "organization_address")
    private String address;

    @OneToMany(mappedBy = "organization")
    private List<Product> products;

    public Organization(String name, String description, Date date, String tel, String mail, String address) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.tel = tel;
        this.mail = mail;
        this.address = address;
    }

    public Organization() {

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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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
