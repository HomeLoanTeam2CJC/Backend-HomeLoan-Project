package com.hexaware.hlmbackend.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexaware.hlmbackend.app.model.AccountDetails;
import com.hexaware.hlmbackend.app.model.CibilData;
import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.model.GurantorDetails;
import com.hexaware.hlmbackend.app.model.PropertyAddress;
import com.hexaware.hlmbackend.app.model.PropertyInfo;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@RestController
@CrossOrigin("*")
@RequestMapping("/step4Api")
public class Step4Controller {
	
	@Autowired
	private HomeLoanServiceInterface hlsi;
	
	@PostMapping("/postStep4/{savedCustomerId}")
	public String insertStep4data(@RequestPart String customerApplication,
			@RequestPart(value = "propertyInfo.propertyDocuments") MultipartFile propertyDocuments,
			@RequestPart(value = "propertyInfo.priceProofs") MultipartFile priceProofs,
			@PathVariable Integer savedCustomerId
			) throws IOException {
		
		ObjectMapper om = new ObjectMapper(); 
		
		Customer cla=om.readValue(customerApplication,Customer.class);
		
		//Fetching customer from Database
		Customer savedCustomer = hlsi.getSavedCustomer(savedCustomerId);
		
		CibilData cd = savedCustomer.getCibilDetails();
		
		cd.setCibilScore(cla.getCibilDetails().getCibilScore());
		cd.setCibilScoreDateTime(cla.getCibilDetails().getCibilScoreDateTime());
		cd.setStatus(cla.getCibilDetails().getStatus());
		cd.setRemarksByOe(cla.getCibilDetails().getRemarksByOe());
		
		
		AccountDetails ad = savedCustomer.getAccountDetails();
		ad.setAccountType(cla.getAccountDetails().getAccountType());
		ad.setAccountBalance(cla.getAccountDetails().getAccountBalance());
		ad.setAccountHolderName(cla.getAccountDetails().getAccountHolderName());
		ad.setAccountStatus(cla.getAccountDetails().getAccountStatus());
		ad.setAccountNumber(cla.getAccountDetails().getAccountNumber());
		
		PropertyInfo pi = savedCustomer.getPropertyInfo();
		
		pi.setPropertyType(cla.getPropertyInfo().getPropertyType());
		pi.setPropertyArea(cla.getPropertyInfo().getPropertyType());
		pi.setConstructionArea(cla.getPropertyInfo().getConstructionArea());
		pi.setPropertyPrice(cla.getPropertyInfo().getPropertyPrice());
		pi.setConstructionPrice(cla.getPropertyInfo().getConstructionPrice());
		pi.setPriceProofs(propertyDocuments.getBytes());
		pi.setPropertyDocuments(priceProofs.getBytes());
		
		PropertyAddress pa = savedCustomer.getPropertyInfo().getPropertyAddress();
		
		pa.setAreaName(cla.getPropertyInfo().getPropertyAddress().getAreaName());
		pa.setCityName(cla.getPropertyInfo().getPropertyAddress().getCityName());
		pa.setDistrict(cla.getPropertyInfo().getPropertyAddress().getDistrict());
		pa.setState(cla.getPropertyInfo().getPropertyAddress().getState());
		pa.setPincode(cla.getPropertyInfo().getPropertyAddress().getPincode());
		pa.setStreetName(cla.getPropertyInfo().getPropertyAddress().getStreetName());
		
		
		pi.setPropertyAddress(pa);
		
		GurantorDetails gd = savedCustomer.getGurantorDetails();
		gd.setGurantorName(cla.getGurantorDetails().getGurantorName());
		gd.setGurantorDateOfBirth(cla.getGurantorDetails().getGurantorDateOfBirth());
		gd.setGurantorMobileNumber(cla.getGurantorDetails().getGurantorMobileNumber());
		gd.setGurantorAadharCardNo(cla.getGurantorDetails().getGurantorAadharCardNo());
		gd.setGurantorAddress(cla.getGurantorDetails().getGurantorAddress());
		gd.setGurantorRelationship(cla.getGurantorDetails().getGurantorRelationship());
//		gd.setCustomer(savedCustomer);
		
		hlsi.insertCibilData(cd);
		hlsi.insertAccountDetails(ad);
		hlsi.insertPropertyInfo(pi);
		hlsi.insertGurantor(gd);
		
		return "Step4 Successfull";
	}

}
