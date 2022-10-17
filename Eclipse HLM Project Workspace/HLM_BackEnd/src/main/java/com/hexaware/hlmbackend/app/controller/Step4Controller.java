package com.hexaware.hlmbackend.app.controller;

import java.io.IOException;

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
import com.hexaware.hlmbackend.app.model.AccountDetails;
import com.hexaware.hlmbackend.app.model.CibilData;
import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.model.PropertyAddress;
import com.hexaware.hlmbackend.app.model.PropertyInfo;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@RestController
@CrossOrigin("*")
@RequestMapping("/step4Api")
public class Step4Controller {
	
	@Autowired
	private HomeLoanServiceInterface hlsi;
	
	@PostMapping("/postStep4")
	public String insertStep4data(@RequestPart String customer,
			@RequestPart(value = "propertyInfo.propertyDocuments") MultipartFile propertyDocuments,
			@RequestPart(value = "propertyInfo.priceProofs") MultipartFile priceProofs
			) throws IOException {
		
		ObjectMapper om = new ObjectMapper(); 
		
		Customer cla=om.readValue(customer,Customer.class);
		
		CibilData cd = new CibilData();
		
		cd.setCibilScore(cla.getCibilDetails().getCibilScore());
		cd.setCibilScoreDateTime(cla.getCibilDetails().getCibilScoreDateTime());
		cd.setStatus(cla.getCibilDetails().getStatus());
		cd.setRemarksByOe(cla.getCibilDetails().getRemarksByOe());
		
		
		AccountDetails ad = new AccountDetails();
		ad.setAccountType(cla.getAccountDetails().getAccountType());
		ad.setAccountBalance(cla.getAccountDetails().getAccountBalance());
		ad.setAccountHolderName(cla.getAccountDetails().getAccountHolderName());
		ad.setAccountStatus(cla.getAccountDetails().getAccountStatus());
		ad.setAccountNumber(cla.getAccountDetails().getAccountNumber());
		
		PropertyInfo pi = new PropertyInfo();
		
		pi.setPropertyType(cla.getPropertyInfo().getPropertyType());
		pi.setPropertyArea(cla.getPropertyInfo().getPropertyType());
		pi.setConstructionArea(cla.getPropertyInfo().getConstructionArea());
		pi.setPropertyPrice(cla.getPropertyInfo().getPropertyPrice());
		pi.setConstructionPrice(cla.getPropertyInfo().getConstructionPrice());
		pi.setPriceProofs(propertyDocuments.getBytes());
		pi.setPropertyDocuments(priceProofs.getBytes());
		
		PropertyAddress pa = new PropertyAddress();
		pa.setAreaName(cla.getPropertyInfo().getPropertyAddress().getAreaName());
		pa.setCityName(cla.getPropertyInfo().getPropertyAddress().getCityName());
		pa.setDistrict(cla.getPropertyInfo().getPropertyAddress().getDistrict());
		pa.setState(cla.getPropertyInfo().getPropertyAddress().getState());
		pa.setPincode(cla.getPropertyInfo().getPropertyAddress().getPincode());
		pa.setStreetName(cla.getPropertyInfo().getPropertyAddress().getStreetName());
		
		pi.setPropertyAddress(pa);
		
		hlsi.insertCibilData(cd);
		hlsi.insertAccountDetails(ad);
		hlsi.insertPropertyInfo(pi);
		
		return "Step4 Successfull";
	}

}
