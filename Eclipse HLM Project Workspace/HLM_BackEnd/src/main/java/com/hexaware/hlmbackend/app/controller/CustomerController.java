package com.hexaware.hlmbackend.app.controller;

import java.io.IOException;

import org.hibernate.type.descriptor.sql.JdbcTypeFamilyInformation.Family;
import org.springframework.http.MediaType;
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
import com.hexaware.hlmbackend.app.model.Address;
import com.hexaware.hlmbackend.app.model.AllPersonalDocuments;
import com.hexaware.hlmbackend.app.model.CibilData;
import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.model.DeligenceReport;
import com.hexaware.hlmbackend.app.model.EducationalInfo;
import com.hexaware.hlmbackend.app.model.EnquiryForm;
import com.hexaware.hlmbackend.app.model.FamilyInfo;
import com.hexaware.hlmbackend.app.model.GurantorDetails;
import com.hexaware.hlmbackend.app.model.LoanAgreement;
import com.hexaware.hlmbackend.app.model.LoanDisbursement;
import com.hexaware.hlmbackend.app.model.Profession;
import com.hexaware.hlmbackend.app.model.PropertyAddress;
import com.hexaware.hlmbackend.app.model.PropertyInfo;
import com.hexaware.hlmbackend.app.model.SanctionLetter;

@RestController
@CrossOrigin("*")
@RequestMapping("/customerApi")
public class CustomerController {
	
	@PostMapping(value = "/newApplication", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String insertCustomerApplication(
			@RequestPart String customerApplication,
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
			@RequestPart(value = "propertyInfo.priceProofs") MultipartFile priceProofs
			) throws IOException {
		
		
		ObjectMapper om = new ObjectMapper(); 
		Customer cla = om.readValue(customerApplication, Customer.class);
		
		Customer c = new Customer();
		
		c.setCustomerName(cla.getCustomerName());
		c.setCustomerDateOfBirth(cla.getCustomerDateOfBirth());
		c.setCustomerAge(cla.getCustomerAge());
		c.setCustomerGender(cla.getCustomerGender());
		c.setCustomerEmail(cla.getCustomerEmail());
		c.setCustomerMobileNumber(cla.getCustomerMobileNumber());
		c.setCustomerMobileNumber2(cla.getCustomerMobileNumber2());
		c.setCustomerAmountPaidForHome(cla.getCustomerAmountPaidForHome());
		c.setCustomerTotalLoanRequired(cla.getCustomerTotalLoanRequired());
		c.setDeligenceStatus(cla.getDeligenceStatus());
		c.setDoReportBmResponseStatus(cla.getDoReportBmResponseStatus());
		c.setDoReportBmResponse(cla.getDoReportBmResponse());
		c.setSanctionLetterStatus(cla.getSanctionLetterStatus());
		c.setCustomerAcceptanceStatus(cla.getCustomerAcceptanceStatus());
		c.setLoanAgreementStatus(cla.getLoanAgreementStatus());
		c.setLoanAgreementBmSignStatus(cla.getLoanAgreementBmSignStatus());
		c.setLoanAgreementCustomerSignStatus(cla.getLoanAgreementCustomerSignStatus());
		c.setLoanDisbursementStatus(cla.getLoanDisbursementStatus());
		
		Address addr = new Address();
		addr.setHouseNumber(cla.getCustomerAddress().getHouseNumber());
		addr.setStreetName(cla.getCustomerAddress().getStreetName());
		addr.setAreaName(cla.getCustomerAddress().getAreaName());
		addr.setCityName(cla.getCustomerAddress().getCityName());
		addr.setDistrict(cla.getCustomerAddress().getDistrict());
		addr.setState(cla.getCustomerAddress().getState());
		addr.setPincode(cla.getCustomerAddress().getPincode());
		
		c.setCustomerAddress(addr);
		
		
		EducationalInfo ei = new EducationalInfo();
		ei.setEducationType(cla.getEducationalInfo().getEducationType());
		
		c.setEducationalInfo(ei);
		
		AllPersonalDocuments apd =new AllPersonalDocuments();
		apd.setAddressProof(addressProof.getBytes());
		apd.setPanCard(panCard.getBytes());
		apd.setIncomeTax(incomeTax.getBytes());
		apd.setAadharCard(aadharCard.getBytes());
		apd.setPhoto(photo.getBytes());
		apd.setThumbPrint(thumbPrint.getBytes());
		apd.setSignature(signature.getBytes());
		apd.setBankCheque(bankCheque.getBytes());
		apd.setSalarySlips(salarySlips.getBytes());
		
		c.setAllPersonalDocuments(apd);
		
		FamilyInfo fi = new FamilyInfo();
		fi.setFatherName(cla.getFamilyInfo().getFatherName());
		fi.setMotherName(cla.getFamilyInfo().getMotherName());
		fi.setSpouseName(cla.getFamilyInfo().getSpouseName());
		fi.setNoOfFamilyMembers(cla.getFamilyInfo().getNoOfFamilyMembers());
		fi.setNoOfChildren(cla.getFamilyInfo().getNoOfChildren());
		fi.setMaritalStatus(cla.getFamilyInfo().getMaritalStatus());
		fi.setFamilyIncome(cla.getFamilyInfo().getFamilyIncome());
		
		c.setFamilyInfo(fi);
		
		Profession pf = new Profession();
		pf.setProfessionType(cla.getProfession().getProfessionType());
		pf.setProfessionDesignation(cla.getProfession().getProfessionDesignation());
		pf.setProfessionSalary(cla.getProfession().getProfessionSalary());
		
		c.setProfession(pf);
		
		CibilData cibil = new CibilData();
		cibil.setCibilScore(cla.getCibilDetails().getCibilScore());
		cibil.setCibilScoreDateTime(cla.getCibilDetails().getCibilScoreDateTime());
		cibil.setStatus(cla.getCibilDetails().getStatus());
		cibil.setRemarksByOe(cla.getCibilDetails().getRemarksByOe());
		
		c.setCibilDetails(cibil);
		
		AccountDetails ad = new AccountDetails();
		ad.setAccountType(cla.getAccountDetails().getAccountType());
		ad.setAccountBalance(cla.getAccountDetails().getAccountBalance());
		ad.setAccountHolderName(cla.getAccountDetails().getAccountHolderName());
		ad.setAccountStatus(cla.getAccountDetails().getAccountStatus());
		ad.setAccountNumber(cla.getAccountDetails().getAccountNumber());
		
		c.setAccountDetails(ad);
			
		PropertyInfo pi = new PropertyInfo();
		pi.setPropertyType(cla.getPropertyInfo().getPropertyType());
		pi.setPropertyArea(cla.getPropertyInfo().getPropertyArea());
		pi.setConstructionArea(cla.getPropertyInfo().getConstructionArea());
		pi.setPropertyPrice(cla.getPropertyInfo().getPropertyPrice());
		pi.setConstructionPrice(cla.getPropertyInfo().getConstructionPrice());
		pi.setPropertyDocuments(propertyDocuments.getBytes());
		pi.setPriceProofs(priceProofs.getBytes());
		PropertyAddress paddr = new PropertyAddress();
		paddr.setStreetName(cla.getPropertyInfo().getPropertyAddresss().getStreetName());
		paddr.setAreaName(cla.getPropertyInfo().getPropertyAddresss().getAreaName());
		paddr.setCityName(cla.getPropertyInfo().getPropertyAddresss().getCityName());
		paddr.setDistrict(cla.getPropertyInfo().getPropertyAddresss().getDistrict());
		paddr.setState(cla.getPropertyInfo().getPropertyAddresss().getState());
		paddr.setPincode(cla.getPropertyInfo().getPropertyAddresss().getPincode());
		
		pi.setPropertyAddresss(paddr);
		c.setPropertyInfo(pi);
		
		
		
		GurantorDetails gd = new GurantorDetails();
		
		DeligenceReport dr = new DeligenceReport();
		
		SanctionLetter sl = new SanctionLetter();
		
		LoanAgreement la = new LoanAgreement();
		
		LoanDisbursement ld = new LoanDisbursement();
		
		
		return null;
	}

	
}
