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
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@RestController
@CrossOrigin("*")
@RequestMapping("/step9Api")
public class Step9Controller {
	
	@Autowired
	private HomeLoanServiceInterface hlsi;
	
	@PostMapping(value = "/PostStep9api")
	public String InsertStep7Data(@RequestPart String customerApplication) throws JsonMappingException, JsonProcessingException
	{
		ObjectMapper om = new ObjectMapper(); 
		Customer cla = om.readValue(customerApplication, Customer.class);
		
		Customer c = new Customer();
		c.setLoanAgreementBmSignStatus(cla.getLoanAgreementBmSignStatus());
		
		hlsi.insertStep9Data(c);
		
		return "Step9 Saved";
	}

}
