package com.hexaware.hlmbackend.app.dto;

public class LedgerDTO {
	
	private Integer ledgerId;
	private String ledgerCreatedDate;
	private Double totalLoanAmount;
	private Integer tenure;
	private Double monthlyEMI;
	private Double amountPaidTillDate;
	private Double remainingAmount;
	private String nextEmiDateStart;
	private String nextEmiDateEnd;
	private Integer defaulterCount;
	private String previousEmiStatus;
	private String currentMonthEmiStatus;
	private String loanEndDate;
	private String loanStatus;
	private boolean defaulterStatus;

}
