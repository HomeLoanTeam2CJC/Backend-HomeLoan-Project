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
import com.hexaware.hlmbackend.app.model.SanctionLetter;
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
		c.setSanctionLetterStatus(cla.getSanctionLetterStatus());
		c.setCustomerAcceptanceStatus(cla.getCustomerAcceptanceStatus());
		
		SanctionLetter sl = new SanctionLetter();
		
		sl.setSanctionDate(c.getSanctionLetter().getSanctionDate());
		sl.setApplicantName(c.getSanctionLetter().getApplicantName());
		sl.setContactDetails(c.getSanctionLetter().getContactDetails());
		sl.setMaxSanctionAmount(c.getSanctionLetter().getMaxSanctionAmount());
		sl.setMaxEmi(c.getSanctionLetter().getMaxEmi());
		sl.setAverageTenure(c.getSanctionLetter().getAverageTenure());
		sl.setValidity(c.getSanctionLetter().getValidity());
		
//	hlsi.insertStep5Data(c);
	return "Step5 saved";
		
	}
	
}
