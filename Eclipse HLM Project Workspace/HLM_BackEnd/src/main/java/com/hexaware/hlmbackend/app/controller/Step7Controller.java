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
import com.hexaware.hlmbackend.app.model.SanctionLetter;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@RestController
@CrossOrigin("*")
@RequestMapping("/step7Api")
public class Step7Controller {
	
	@Autowired
	private HomeLoanServiceInterface hlsi;
	
	@PostMapping(value = "/PostStep7api")
	public String InsertStep7Data(@RequestPart String customerApplication) throws JsonMappingException, JsonProcessingException
	{
		ObjectMapper om = new ObjectMapper(); 
		Customer cla = om.readValue(customerApplication, Customer.class);
		
		Customer c = new Customer();
		c.setSanctionLetterStatus(cla.getSanctionLetterStatus());
		c.setCustomerAcceptanceStatus(cla.getCustomerAcceptanceStatus());
		
		SanctionLetter sl = new SanctionLetter();
		sl.setSanctionDate(cla.getSanctionLetter().getSanctionDate());
		sl.setApplicantName(cla.getSanctionLetter().getApplicantName());
		sl.setContactDetails(cla.getSanctionLetter().getContactDetails());
		sl.setMaxSanctionAmount(cla.getSanctionLetter().getMaxSanctionAmount());
		sl.setMaxEmi(cla.getSanctionLetter().getMaxEmi());
		sl.setAverageTenure(cla.getSanctionLetter().getAverageTenure());
		sl.setValidity(cla.getSanctionLetter().getValidity());
		
		c.setSanctionLetter(sl);
		
		hlsi.insertStep7Data(c);
		return "Step7 Saved";
	}

}
