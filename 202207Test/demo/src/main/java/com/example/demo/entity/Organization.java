package com.example.demo.entity;

public class Organization {
    private Long organizationId;
    private String createDate;
    private String organizationName;
    private String organizationIntro;
    private String organizationTel;
    private String organizationMail;
    private String organizationAddr;

public Organization(){
    super();
}
  public Organization(Long organizationId, String createDate, String organizationName, String organizationIntro, String organizationTel, String organizationMail, String organizationAddr) throws Exception {
    this.organizationId = organizationId;
    this.organizationName = organizationName;
    this.organizationAddr = organizationAddr;
    this.organizationIntro = organizationIntro;
    this.organizationTel = organizationTel;
    this.organizationMail = organizationMail;
    this.createDate = createDate;
  }


    public Organization(Long long1, String string, String string2, int int1) {
}


    public Long getOrganizationId() {
        return this.organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOrganizationName() {
        return this.organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationIntro() {
        return this.organizationIntro;
    }

    public void setOrganizationIntro(String organizationIntro) {
        this.organizationIntro = organizationIntro;
    }

    public String getOrganizationTel() {
        return this.organizationTel;
    }

    public void setOrganizationTel(String organizationTel) {
        this.organizationTel = organizationTel;
    }

    public String getOrganizationMail() {
        return this.organizationMail;
    }

    public void setOrganizationMail(String organizationMail) {
        this.organizationMail = organizationMail;
    }

    public String getOrganizationAddr() {
        return this.organizationAddr;
    }

    public void setOrganizationAddr(String organizationAddr) {
        this.organizationAddr = organizationAddr;
    }


    public int getId() {
        return 0;
    }

  
}

