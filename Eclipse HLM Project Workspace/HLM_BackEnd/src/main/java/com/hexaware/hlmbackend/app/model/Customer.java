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
	
	//phase1
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
	
	//phase 2
	//step5
	//Deligence Officer
	private String deligenceStatus;
	@OneToOne (cascade = CascadeType.ALL)
	private DeligenceReport deligenceReport;
	
	//phase3
	//step6
	//Branch Manager
	private String doReportBmResponseStatus;
	private String doReportBmResponse;
	
	//phase 4
	//step7
	//Branch Manager
	@OneToOne (cascade = CascadeType.ALL)
	private SanctionLetter sanctionLetter;	
	private String sanctionLetterStatus;
	private String customerAcceptanceStatus;
	
	//phase 5
	//step8
	//Deligence Officer
	private String loanAgreementStatus;	
	@OneToOne (cascade = CascadeType.ALL)
	private LoanAgreement loanAgreement;	

	//phase 6
	//step9
	private String loanAgreementBmSignStatus;

	//phase 7
	//step 10
	//Account Manager
	private String loanDisbursementStatus;
	@OneToOne (cascade = CascadeType.ALL)
	private LoanDisbursement loanDisbursement;
	

//	private String loanAgreementCustomerSignStatus;
}
