package com.example.interntestbackend.service;

import java.util.List;

import com.example.interntestbackend.model.Organization;

public interface OrganizationService {

    void createOrganization(Organization organization);

    Organization getOrganizationById(int id);

    List<Organization> getAllOrganization();

    void updateOrganization(Organization organization);

    void deleteOrganization(int id);

    List<String> getAllOrganizationsName();

    List<String> multipleQuery(String organizationName, String productName);

}
