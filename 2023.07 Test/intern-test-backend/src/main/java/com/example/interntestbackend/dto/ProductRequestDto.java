package com.example.interntestbackend.dto;

import com.example.interntestbackend.model.Organization;

public class ProductRequestDto {
    private String name;
    private String description;
    private String sort;
    private String price;
    private int organizationId;

    public ProductRequestDto(String name, String description, String sort, String price, int organizationId) {
        this.name = name;
        this.description = description;
        this.sort = sort;
        this.price = price;
        this.organizationId = organizationId;
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

    public void setOrganization(int organizationId) {
        this.organizationId = organizationId;
    }
}
