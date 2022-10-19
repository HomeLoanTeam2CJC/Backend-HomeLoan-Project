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
public class LoanAgreement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer loanAgreementId;
	private String loanAgreementName;
	private String applicantName;
	private Integer contactDetails;
	private Double loanAmountSanctioned;
	private String interestType;
	private Integer rateOfInterest;
	private Integer loanTenure;
	private Double monthlyEmiAmount;
	private String modeOfPayment;
	private String remarks;
	private String status;
	
//	@OneToOne(cascade = CascadeType.ALL,mappedBy = "loanAgreement")
//	private Customer customer;
	
	
}
