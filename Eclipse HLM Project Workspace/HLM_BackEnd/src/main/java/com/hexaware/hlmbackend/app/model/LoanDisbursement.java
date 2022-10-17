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
public class LoanDisbursement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer agreementId;
	private Integer loanNumber;
	private String agreementDate;
	private String amountPayType;
	private Double totalLoanAmount;
	private String bankName;
	private Long accountNumber;
	private String ifscCode;
	private String accountType;
	private Double transferAmount;
	private String paymentStatus;
	private String amountPaidDate;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "loanDisbursement")
	private Customer customer;
	
}
