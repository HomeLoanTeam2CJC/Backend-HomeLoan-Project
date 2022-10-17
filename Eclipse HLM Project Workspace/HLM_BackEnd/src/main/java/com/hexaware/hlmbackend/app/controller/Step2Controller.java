package com.hexaware.hlmbackend.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.hexaware.hlmbackend.app.model.FamilyInfo;
import com.hexaware.hlmbackend.app.model.Profession;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@RestController
@CrossOrigin("*")
@RequestMapping("/step2Api")
public class Step2Controller {

	@Autowired
	private HomeLoanServiceInterface hlsi;
	
	@PostMapping("/postStep2")
	public String saveStep2(@RequestPart String customer) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper om = new ObjectMapper(); 
		
		Customer c=om.readValue(customer,Customer.class);
		
		
		Address addr1 = new Address();
		addr1.setHouseNumber(c.getCustomerAddress().getHouseNumber());
		addr1.setStreetName(c.getCustomerAddress().getStreetName());
		addr1.setAreaName(c.getCustomerAddress().getAreaName());
		addr1.setCityName(c.getCustomerAddress().getCityName());
		addr1.setDistrict(c.getCustomerAddress().getDistrict());
		addr1.setState(c.getCustomerAddress().getState());
		addr1.setPincode(c.getCustomerAddress().getPincode());
		
		
		//FamilyInfo fin = om.readValue(step2,FamilyInfo.class);
		
		FamilyInfo fi = new FamilyInfo();
		fi.setFatherName(c.getFamilyInfo().getFatherName());
		fi.setMotherName(c.getFamilyInfo().getMotherName());
		fi.setSpouseName(c.getFamilyInfo().getSpouseName());
		fi.setNoOfFamilyMembers(c.getFamilyInfo().getNoOfFamilyMembers());
		fi.setNoOfChildren(c.getFamilyInfo().getNoOfChildren());
		fi.setMaritalStatus(c.getFamilyInfo().getMaritalStatus());
		fi.setFamilyIncome(c.getFamilyInfo().getFamilyIncome());
		
	//	Profession p=om.readValue(step2, Profession.class);
		
		Profession pf = new Profession();
		pf.setProfessionType(c.getProfession().getProfessionType());
		pf.setProfessionDesignation(c.getProfession().getProfessionDesignation());
		pf.setProfessionSalary(c.getProfession().getProfessionSalary());
		
		hlsi.insertCustomerAddress(addr1);
		hlsi.insertCustomerFamilyInfo(fi);
		hlsi.insertCustomerProffesion(pf);
		
		return "Step2 Successfull";
		
	}
	
}
