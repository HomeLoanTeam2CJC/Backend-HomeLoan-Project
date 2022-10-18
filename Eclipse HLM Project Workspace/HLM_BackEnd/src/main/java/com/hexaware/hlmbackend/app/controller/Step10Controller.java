package com.hexaware.hlmbackend.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.model.LoanDisbursement;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@RestController
@CrossOrigin("*")
@RequestMapping("/step10Api")
public class step10Controller {

	@Autowired
	private HomeLoanServiceInterface hlsi;
	
	@PostMapping(value = "/PostStep10api")
	public String InsertStep7Data(@RequestPart String customerApplication) throws JsonMappingException, JsonProcessingException
	{
		ObjectMapper om = new ObjectMapper(); 
		Customer cla = om.readValue(customerApplication, Customer.class);
		
		Customer c = new Customer();
		c.setLoanDisbursementStatus(cla.getLoanDisbursementStatus());
		
		LoanDisbursement ld = new LoanDisbursement();
		ld.setLoanNumber(cla.getLoanDisbursement().getLoanNumber());
		ld.setAgreementDate(cla.getLoanDisbursement().getAgreementDate());
		ld.setAmountPayType(cla.getLoanDisbursement().getAmountPayType());
		ld.setTotalLoanAmount(cla.getLoanDisbursement().getTotalLoanAmount());
		ld.setBankName(cla.getLoanDisbursement().getBankName());
		ld.setAccountNumber(cla.getLoanDisbursement().getAccountNumber());
		ld.setIfscCode(cla.getLoanDisbursement().getIfscCode());
		ld.setAccountType(cla.getLoanDisbursement().getAccountType());
		ld.setTransferAmount(cla.getLoanDisbursement().getTransferAmount());
		ld.setPaymentStatus(cla.getLoanDisbursement().getPaymentStatus());
		ld.setAmountPaidDate(cla.getLoanDisbursement().getAmountPaidDate());
		
		c.setLoanDisbursement(ld);
		
		hlsi.insertstep10Data(c);
		
		return "step10 Saved";	
	}
}
