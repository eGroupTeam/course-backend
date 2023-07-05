package com.example.interntestbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.interntestbackend.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    Organization findById(int id);
}
