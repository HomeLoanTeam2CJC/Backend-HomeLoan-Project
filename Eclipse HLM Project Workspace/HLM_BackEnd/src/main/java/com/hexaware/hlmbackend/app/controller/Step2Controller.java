package com.hexaware.hlmbackend.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexaware.hlmbackend.app.model.Address;
import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.model.EducationalInfo;
import com.hexaware.hlmbackend.app.model.FamilyInfo;
import com.hexaware.hlmbackend.app.model.Profession;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@RestController
@CrossOrigin("*")
@RequestMapping("/step2Api")
public class Step2Controller {

	@Autowired
	private HomeLoanServiceInterface hlsi;
	
	@PostMapping("/postStep2/{savedCustomerId}")
	public String saveStep2(
			@RequestPart String customerApplication,
			@PathVariable Integer savedCustomerId) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper om = new ObjectMapper(); 
		
		Customer cla=om.readValue(customerApplication,Customer.class);
//		Customer c = new Customer();
		
		System.out.println("Saved customer Id :"+savedCustomerId);
		
		//Fetching customer from Database
		Customer savedCustomer = hlsi.getSavedCustomer(savedCustomerId);
		
		//Fetching
		EducationalInfo ei = savedCustomer.getEducationalInfo();
		
		
//		EducationalInfo ei = new EducationalInfo();
		
		ei.setEducationType(cla.getEducationalInfo().getEducationType());
		//new changes for bidirectional
//		ei.setCustomer(savedCustomer);
		
		//FamilyInfo fin = om.readValue(step2,FamilyInfo.class);
		
		FamilyInfo fi = savedCustomer.getFamilyInfo();
		fi.setFatherName(cla.getFamilyInfo().getFatherName());
		fi.setMotherName(cla.getFamilyInfo().getMotherName());
		fi.setSpouseName(cla.getFamilyInfo().getSpouseName());
		fi.setNoOfFamilyMembers(cla.getFamilyInfo().getNoOfFamilyMembers());
		fi.setNoOfChildren(cla.getFamilyInfo().getNoOfChildren());
		fi.setMaritalStatus(cla.getFamilyInfo().getMaritalStatus());
		fi.setFamilyIncome(cla.getFamilyInfo().getFamilyIncome());
		
		//new changes for bidirectional
//		fi.setCustomer(savedCustomer);
		
	//	Profession p=om.readValue(step2, Profession.class);
		
		Profession pf = savedCustomer.getProfession();
		pf.setProfessionType(cla.getProfession().getProfessionType());
		pf.setProfessionDesignation(cla.getProfession().getProfessionDesignation());
		pf.setProfessionSalary(cla.getProfession().getProfessionSalary());
		
		//new changes for bidirectional
//		pf.setCustomer(savedCustomer);
		
		hlsi.insertCustomerEducation(ei);
		hlsi.insertCustomerFamilyInfo(fi);
		hlsi.insertCustomerProffesion(pf);
		
		return "Step2 Successfull";
		
	}
	
}
