package com.example.interntestbackend.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.example.interntestbackend.model.Organization;
import com.example.interntestbackend.repository.OrganizationRepository;
import com.example.interntestbackend.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public void createOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

    @Override
    public Organization getOrganizationById(int id) {
        return organizationRepository.findById(id);
    }

    @Override
    public List<Organization> getAllOrganization() {
        return organizationRepository.findAll();
    }

    @Override
    public void updateOrganization(Organization organization) {
        if (organizationRepository.existsById(organization.getId())) {
            organizationRepository.save(organization);
        }
    }

    @Override
    public void deleteOrganization(int id) {
        organizationRepository.deleteById(id);
    }

    @Override
    public List<String> getAllOrganizationsName() {
        List<Organization> organizationList = organizationRepository.findAll();
        List<String> nameList = new ArrayList<>();
        for (Organization organization : organizationList) {
            nameList.add(organization.getName());
        }
        return nameList;
    }

    @Override
    public List<String> multipleQuery(String organizationName, String productName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'multipleQuery'");
    }
    
}
