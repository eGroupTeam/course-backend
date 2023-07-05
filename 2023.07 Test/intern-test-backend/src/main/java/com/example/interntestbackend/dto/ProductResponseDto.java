package com.example.interntestbackend.dto;

public class ProductResponseDto {
    private int id;
    private String name;
    private String description;
    private String sort;
    private String price;
    private int organizationId;

    public ProductResponseDto(int id, String name, String description, String sort, String price, int organizationId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sort = sort;
        this.price = price;
        this.organizationId = organizationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }
}
