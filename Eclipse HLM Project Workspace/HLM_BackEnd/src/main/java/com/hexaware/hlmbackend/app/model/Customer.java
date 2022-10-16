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
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	private String customerName;
	private String customerDateOfBirth;
	private Integer customerAge;
	private String customerGender;
	private String customerEmail;
	private Integer customerMobileNumber;
	private Integer customerMobileNumber2;
	
	
	private Integer customerAmountPaidForHome;
	private Integer customerTotalLoanRequired;
	private String deligenceStatus;
	private String doReportBmResponseStatus;
	private String doReportBmResponse;
	private String sanctionLetterStatus;
	private String customerAcceptanceStatus;
	private String loanAgreementStatus;	
	private String loanAgreementBmSignStatus;
	private String loanAgreementCustomerSignStatus;
	private String loanDisbursementStatus;
	
	@OneToOne (cascade = CascadeType.ALL)
	private Address customerAddress;
	@OneToOne (cascade = CascadeType.ALL)
	private EducationalInfo educationalInfo;
	@OneToOne (cascade = CascadeType.ALL)
	private AllPersonalDocuments allPersonalDocuments;
	@OneToOne (cascade = CascadeType.ALL)
	private FamilyInfo familyInfo;
	@OneToOne (cascade = CascadeType.ALL)
	private Profession profession;
	@OneToOne (cascade = CascadeType.ALL)
	private CibilData cibilDetails;
	@OneToOne (cascade = CascadeType.ALL)
	private AccountDetails accountDetails;
	@OneToOne (cascade = CascadeType.ALL)
	private PropertyInfo propertyInfo;
	@OneToOne (cascade = CascadeType.ALL)
	private GurantorDetails gurantorDetails;	
	@OneToOne (cascade = CascadeType.ALL)
	private DeligenceReport deligenceReport;	
	@OneToOne (cascade = CascadeType.ALL)
	private SanctionLetter sanctionLetter;	
	@OneToOne (cascade = CascadeType.ALL)
	private LoanAgreement loanAgreement;	
	@OneToOne (cascade = CascadeType.ALL)
	private LoanDisbursement loanDisbursement;
	
}
