package com.hexaware.hlmbackend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hlmbackend.app.model.GurantorDetails;

@Repository
public interface GurantorRepository extends JpaRepository<GurantorDetails, Integer> {

}
