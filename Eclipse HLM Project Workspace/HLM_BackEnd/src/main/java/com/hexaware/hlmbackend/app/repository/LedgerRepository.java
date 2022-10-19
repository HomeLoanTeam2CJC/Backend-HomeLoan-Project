package com.hexaware.hlmbackend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hlmbackend.app.model.Ledger;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger, Integer> {

}
