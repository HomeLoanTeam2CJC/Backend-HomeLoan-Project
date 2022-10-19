package com.hexaware.hlmbackend.app.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.model.DeligenceReport;
import com.hexaware.hlmbackend.app.model.FieldInvestigation;
import com.hexaware.hlmbackend.app.model.FinancialCheck;
import com.hexaware.hlmbackend.app.model.SanctionLetter;
import com.hexaware.hlmbackend.app.model.TechnicalCheck;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@RestController
@CrossOrigin("*")
@RequestMapping("/step5Api")

public class Step5Controller {
	@Autowired
	private HomeLoanServiceInterface hlsi;
	
	@PostMapping("/postStep5/{savedCustomerId}")
	public String InsertStep5Data(
			@RequestPart String customerApplication,
			@PathVariable Integer savedCustomerId) throws JsonMappingException, JsonProcessingException
	{
		ObjectMapper om = new ObjectMapper(); 
		DeligenceReport cla = om.readValue(customerApplication, DeligenceReport.class);
		
		Customer c = new Customer();
		
		//Fetching customer from Database
		Customer savedCustomer = hlsi.getSavedCustomer(savedCustomerId);
		
		
		c.setCustomerId(savedCustomerId);
		c.setCustomerName(savedCustomer.getCustomerName());
		c.setCustomerDateOfBirth(savedCustomer.getCustomerDateOfBirth());
		
		
		
		//need to set and get all fields of customer, from savedCustomer to Customer c
		
//		c.setDeligenceStatus(cla.getDeligenceStatus());
	
		DeligenceReport dr = savedCustomer.getDeligenceReport();
		
		
		FieldInvestigation fin = savedCustomer.getDeligenceReport().getFieldInvestigation();
		fin.setAddressValidity(cla.getFieldInvestigation().getAddressValidity());
		fin.setCompanyDetailsValidity(cla.getFieldInvestigation().getCompanyDetailsValidity());
		fin.setPropertyLegality(cla.getFieldInvestigation().getPropertyLegality());	
		
		dr.setFieldInvestigation(fin);
		
		
		
		FinancialCheck fc = savedCustomer.getDeligenceReport().getFinancialCheck();
		fc.setCibilScore(cla.getFinancialCheck().getCibilScore());
		fc.setNetIncome(cla.getFinancialCheck().getNetIncome());
		
		dr.setFinancialCheck(fc);
		
		
		
		TechnicalCheck tc = savedCustomer.getDeligenceReport().getTechnicalCheck();
		tc.setPropertyVisit(cla.getTechnicalCheck().getPropertyVisit());
		tc.setPropertyValuation(cla.getTechnicalCheck().getPropertyValuation());
		
		dr.setTechnicalCheck(tc);
		
		c.setDeligenceReport(dr);
	
		hlsi.insertStep5Data(c);
		
	return "Step5 saved";
		
	}
	
}
