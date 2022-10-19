package com.hexaware.hlmbackend.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class AccountDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer accountId;
	private String accountType;
	private Double accountBalance;
	private String accountHolderName;
	private String accountStatus;
	private Long accountNumber;
	
	
//	@OneToOne(cascade = CascadeType.ALL,mappedBy = "accountDetails")
//	private Customer customer;
	
}
