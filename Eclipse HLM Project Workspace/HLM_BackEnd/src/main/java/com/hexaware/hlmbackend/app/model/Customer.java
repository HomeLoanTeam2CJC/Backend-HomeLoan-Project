package com.hexaware.hlmbackend.app.model;

import javax.persistence.OneToOne;

public class Customer {

	private Integer customerId;
	private String customerName;
	private String customerDateOfBirth;
	private Integer customerAge;
	private String customerGender;
	private String customerEmail;
	private Integer customerMobileNumber;
	private Integer customerMobileNumber2;
	private String customerAddress;
	private Integer customerAmountPaidForHome;
	private Integer customerTotalLoanRequired;
	
	@OneToOne
	private EducationalInfo educationalInfo;
	@OneToOne
	private AllPersonalDocuments allPersonalDocuments;
	@OneToOne
	private FamilyInfo familyInfo;
	@OneToOne
	private Profession profession;
	@OneToOne
	private CibilData CibilData;
	@OneToOne
	private AccountDetails accountDetails;
	@OneToOne
	private PropertyInfo propertyIno;
	@OneToOne
	private GurantorDetails gurantorDetails;
	
	private String deligenceStatus;
	@OneToOne
	private DeligenceReport deligenceReport;
	private String doReportBmResponseStatus;
	private String doReportBmResponse;
	private String sanctionLetterStatus;
	@OneToOne
	private SanctionLetter sanctionLetter;
	private String customerAcceptanceStatus;
	private String loanAgreementStatus;
	
	@OneToOne
	private LoanAgreement loanAgreement;
	private String loanAgreementBmSignStatus;
	private String loanAgreementCustomerSignStatus;
	private String loanDisbursementStatus;
	@OneToOne
	private LoanDisbursement loanDisbursement;
	
}
