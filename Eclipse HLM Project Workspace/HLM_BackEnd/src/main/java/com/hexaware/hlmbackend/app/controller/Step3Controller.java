package com.hexaware.hlmbackend.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexaware.hlmbackend.app.model.AllPersonalDocuments;
import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@RestController
@CrossOrigin("*")
@RequestMapping("/step3Api")
public class Step3Controller {
	
	@Autowired
	private HomeLoanServiceInterface hlsi;
	
	@PostMapping(value = "/postDocumentsApplication", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String insertDocumentsApplication(
			@RequestPart(value="allPersonalDocuments.addressProof")MultipartFile addressProof,
			@RequestPart(value="allPersonalDocuments.panCard")MultipartFile panCard,
			@RequestPart(value="allPersonalDocuments.incomeTax")MultipartFile incomeTax,
			@RequestPart(value="allPersonalDocuments.aadharCard")MultipartFile aadharCard,
			@RequestPart(value="allPersonalDocuments.photo")MultipartFile photo,
			@RequestPart(value="allPersonalDocuments.thumbPrint")MultipartFile thumbPrint,
			@RequestPart(value="allPersonalDocuments.signature")MultipartFile signature,
			@RequestPart(value="allPersonalDocuments.bankCheque")MultipartFile bankCheque,
			@RequestPart(value="allPersonalDocuments.salarySlips")MultipartFile salarySlips,
			@RequestPart(value = "propertyInfo.propertyDocuments") MultipartFile propertyDocuments,
			@RequestPart(value = "propertyInfo.priceProofs") MultipartFile priceProofs,
			@PathVariable Integer savedCustomerId
			) throws IOException {
		
//		ObjectMapper om = new ObjectMapper(); 
		
//		Customer c = new Customer();
		
		//Fetching customer from Database
		Customer savedCustomer = hlsi.getSavedCustomer(savedCustomerId);
		
		AllPersonalDocuments apd =savedCustomer.getAllPersonalDocuments();
		apd.setAddressProof(addressProof.getBytes());
		apd.setPanCard(panCard.getBytes());
		apd.setIncomeTax(incomeTax.getBytes());
		apd.setAadharCard(aadharCard.getBytes());
		apd.setPhoto(photo.getBytes());
		apd.setThumbPrint(thumbPrint.getBytes());
		apd.setSignature(signature.getBytes());
		apd.setBankCheque(bankCheque.getBytes());
		apd.setSalarySlips(salarySlips.getBytes());
		
//		c.setAllPersonalDocuments(apd);
		
		hlsi.documentsUpload(apd);
		return "Documents Uploaded";
	}
			
			
			

}
