package com.hexaware.hlmbackend.app.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/step5api")

public class Step5Controller {
	@Autowired
	private HomeLoanServiceInterface hlsi;
	
	@PostMapping(value = "/PostStep5api")
	public String InsertStep5Data(@RequestPart String customerApplication) throws JsonMappingException, JsonProcessingException
	{
		ObjectMapper om = new ObjectMapper(); 
		Customer cla = om.readValue(customerApplication, Customer.class);
		
		Customer c = new Customer();		
		c.setDeligenceStatus(cla.getDeligenceStatus());
	
		DeligenceReport dr = new DeligenceReport();
		dr.setFieldInvestigation(cla.getDeligenceReport().getFieldInvestigation());
		
		FieldInvestigation fin=new FieldInvestigation();
		fin.setAddressValidity(cla.getDeligenceReport().getFieldInvestigation().getAddressValidity());
		fin.setCompanyDetailsValidity(cla.getDeligenceReport().getFieldInvestigation().getCompanyDetailsValidity());
		fin.setPropertyLegality(cla.getDeligenceReport().getFieldInvestigation().getPropertyLegality());	
		
		dr.setFinancialCheck(cla.getDeligenceReport().getFinancialCheck());
		
		FinancialCheck fc=new FinancialCheck();
		fc.setCibilScore(cla.getDeligenceReport().getFinancialCheck().getCibilScore());
		fc.setNetIncome(cla.getDeligenceReport().getFinancialCheck().getNetIncome());
		
		dr.setTechnicalCheck(cla.getDeligenceReport().getTechnicalCheck());
		
		TechnicalCheck tc=new TechnicalCheck();
		tc.setPropertyVisit(cla.getDeligenceReport().getTechnicalCheck().getPropertyVisit());
		tc.setPropertyValuation(cla.getDeligenceReport().getTechnicalCheck().getPropertyValuation());
		
		c.setDeligenceReport(dr);
	
		hlsi.insertStep5Data(c);
		
	return "Step5 saved";
		
	}
	
}
