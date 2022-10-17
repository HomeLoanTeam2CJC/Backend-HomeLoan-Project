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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	//salesExecutive
	//step1
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
	@OneToOne (cascade = CascadeType.ALL)
	private Address customerAddress;
	
	//step2
	@OneToOne (cascade = CascadeType.ALL)
	private EducationalInfo educationalInfo; //serviceobject.saveEducationalInfo(ei)
	@OneToOne (cascade = CascadeType.ALL)
	private FamilyInfo familyInfo;	//serviceobject.saveFamilyInfo(fi)
	@OneToOne (cascade = CascadeType.ALL)
	private Profession profession;
	
	//step3
	@OneToOne (cascade = CascadeType.ALL)
	private AllPersonalDocuments allPersonalDocuments;
	
	//step4
	@OneToOne (cascade = CascadeType.ALL)
	private CibilData cibilDetails;
	@OneToOne (cascade = CascadeType.ALL)
	private AccountDetails accountDetails;
	@OneToOne (cascade = CascadeType.ALL)
	private PropertyInfo propertyInfo;
	@OneToOne (cascade = CascadeType.ALL)
	private GurantorDetails gurantorDetails;
	
	
	@OneToOne (cascade = CascadeType.ALL)
	private SanctionLetter sanctionLetter;	
	private String sanctionLetterStatus;
	private String customerAcceptanceStatus;
	
	
	//Deligence Officer
	private String deligenceStatus;
	private String loanAgreementStatus;	
	@OneToOne (cascade = CascadeType.ALL)
	private DeligenceReport deligenceReport;
	@OneToOne (cascade = CascadeType.ALL)
	private LoanAgreement loanAgreement;	
	
	//Branch Manager
	private String doReportBmResponseStatus;
	private String doReportBmResponse;
	private String loanAgreementBmSignStatus;
	private String loanAgreementCustomerSignStatus;
	
	//Account Manager
	private String loanDisbursementStatus;
	@OneToOne (cascade = CascadeType.ALL)
	private LoanDisbursement loanDisbursement;
	
	
	


	
	
	
}
