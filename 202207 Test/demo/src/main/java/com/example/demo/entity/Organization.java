package com.example.demo.entity;

public class Organization {
  private int organizationId;
  private String date;
  private String name;
  private String introduction;
  private String phone;
  private String email;
  private String address;

  public Organization(){
    super();
  }

  public Organization(int organizationId,String date, String name, String introduction, String phone, String email, String address) {
    this.organizationId =organizationId;
    this.date = date;
    this.name = name;
    this.introduction = introduction;
    this.phone = phone;
    this.email = email;
    this.address = address;
  }


  public Organization(int i, String string, Object object, int j, int id, int id2) {
  }


  public int getOrganizationId() {
    return this.organizationId;
  }

  public void setOrganizationId(int organizationId) {
    this.organizationId = organizationId;
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

  public String getIntroduction() {
    return this.introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
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
