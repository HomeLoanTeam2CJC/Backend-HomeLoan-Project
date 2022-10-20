package com.hexaware.hlmbackend.app.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.hexaware.hlmbackend.app.model.Address;
import com.hexaware.hlmbackend.app.model.AllPersonalDocuments;
import com.hexaware.hlmbackend.app.model.CibilData;
import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.model.DeligenceReport;
import com.hexaware.hlmbackend.app.model.EducationalInfo;
import com.hexaware.hlmbackend.app.model.FamilyInfo;
import com.hexaware.hlmbackend.app.model.FieldInvestigation;
import com.hexaware.hlmbackend.app.model.FinancialCheck;
import com.hexaware.hlmbackend.app.model.GurantorDetails;
import com.hexaware.hlmbackend.app.model.Profession;
import com.hexaware.hlmbackend.app.model.PropertyAddress;
import com.hexaware.hlmbackend.app.model.PropertyInfo;
import com.hexaware.hlmbackend.app.model.SanctionLetter;
import com.hexaware.hlmbackend.app.model.TechnicalCheck;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@RestController
@CrossOrigin("*")
@RequestMapping("/step5Api")

public class Step5Controller {
	@Autowired
	private HomeLoanServiceInterface hlsi;
	
	@PostMapping(value = "/postStep5/{savedCustomerId}")
	public String InsertStep5Data(
			@RequestPart String customerApplication,
			@PathVariable Integer savedCustomerId) throws IOException
	{
		ObjectMapper om = new ObjectMapper();
		
		Customer c = new Customer();
		
		//Fetching customer from Database
		Customer savedCustomer = hlsi.getSavedCustomer(savedCustomerId);
		
		//step1
		c.setCustomerId(savedCustomerId);
		c.setCustomerName(savedCustomer.getCustomerName());
		c.setCustomerDateOfBirth(savedCustomer.getCustomerDateOfBirth());
		c.setCustomerAge(savedCustomer.getCustomerAge());
		c.setCustomerGender(savedCustomer.getCustomerGender());
		c.setCustomerEmail(savedCustomer.getCustomerEmail());
		c.setCustomerMobileNumber(savedCustomer.getCustomerMobileNumber());
		c.setCustomerMobileNumber2(savedCustomer.getCustomerMobileNumber2());
		c.setCustomerAmountPaidForHome(savedCustomer.getCustomerAmountPaidForHome());
		c.setCustomerTotalLoanRequired(savedCustomer.getCustomerTotalLoanRequired());
		
		Address addr1 =  savedCustomer.getCustomerAddress();
		addr1.setHouseNumber(savedCustomer.getCustomerAddress().getHouseNumber());
		addr1.setStreetName(savedCustomer.getCustomerAddress().getStreetName());
//		addr1.setAreaName(savedCustomer.getCustomerAddress().getAreaName()); //Trial to check if it gets null value or keeps the old value
//		addr1.setCityName(savedCustomer.getCustomerAddress().getCityName());
		addr1.setDistrict(savedCustomer.getCustomerAddress().getDistrict());
		addr1.setState(savedCustomer.getCustomerAddress().getState());
		addr1.setPincode(savedCustomer.getCustomerAddress().getPincode());
		
		c.setCustomerAddress(addr1);
		
		//step2
		EducationalInfo ei = savedCustomer.getEducationalInfo();
		ei.setEducationType(savedCustomer.getEducationalInfo().getEducationType());
		
		c.setEducationalInfo(ei);
		
		FamilyInfo fi = savedCustomer.getFamilyInfo();
		fi.setFatherName(savedCustomer.getFamilyInfo().getFatherName());
		fi.setMotherName(savedCustomer.getFamilyInfo().getMotherName());
		fi.setSpouseName(savedCustomer.getFamilyInfo().getSpouseName());
		fi.setNoOfFamilyMembers(savedCustomer.getFamilyInfo().getNoOfFamilyMembers());
		fi.setNoOfChildren(savedCustomer.getFamilyInfo().getNoOfChildren());
		fi.setMaritalStatus(savedCustomer.getFamilyInfo().getMaritalStatus());
		fi.setFamilyIncome(savedCustomer.getFamilyInfo().getFamilyIncome());
		
		c.setFamilyInfo(fi);
		
		Profession pf = savedCustomer.getProfession();
		pf.setProfessionType(savedCustomer.getProfession().getProfessionType());
		pf.setProfessionDesignation(savedCustomer.getProfession().getProfessionDesignation());
		pf.setProfessionSalary(savedCustomer.getProfession().getProfessionSalary());
		
		c.setProfession(pf);
		
//		//step3
		AllPersonalDocuments apd =savedCustomer.getAllPersonalDocuments();
		apd.setAddressProof(savedCustomer.getAllPersonalDocuments().getAddressProof());
//		apd.setPanCard(panCard.getBytes());
//		apd.setIncomeTax(incomeTax.getBytes());
//		apd.setAadharCard(aadharCard.getBytes());
//		apd.setPhoto(photo.getBytes());
//		apd.setThumbPrint(thumbPrint.getBytes());
//		apd.setSignature(signature.getBytes());
//		apd.setBankCheque(bankCheque.getBytes());
//		apd.setSalarySlips(salarySlips.getBytes());
		
		//step4
		
		CibilData cd = savedCustomer.getCibilDetails();
		
		cd.setCibilScore(savedCustomer.getCibilDetails().getCibilScore());
		cd.setCibilScoreDateTime(savedCustomer.getCibilDetails().getCibilScoreDateTime());
		cd.setStatus(savedCustomer.getCibilDetails().getStatus());
		cd.setRemarksByOe(savedCustomer.getCibilDetails().getRemarksByOe());
		
		c.setCibilDetails(cd);
		
		AccountDetails ad = savedCustomer.getAccountDetails();
		ad.setAccountType(savedCustomer.getAccountDetails().getAccountType());
		ad.setAccountBalance(savedCustomer.getAccountDetails().getAccountBalance());
		ad.setAccountHolderName(savedCustomer.getAccountDetails().getAccountHolderName());
		ad.setAccountStatus(savedCustomer.getAccountDetails().getAccountStatus());
		ad.setAccountNumber(savedCustomer.getAccountDetails().getAccountNumber());
		
		c.setAccountDetails(ad);
		
		PropertyInfo pi = savedCustomer.getPropertyInfo();
		
		pi.setPropertyType(savedCustomer.getPropertyInfo().getPropertyType());
		pi.setPropertyArea(savedCustomer.getPropertyInfo().getPropertyType());
		pi.setConstructionArea(savedCustomer.getPropertyInfo().getConstructionArea());
		pi.setPropertyPrice(savedCustomer.getPropertyInfo().getPropertyPrice());
		pi.setConstructionPrice(savedCustomer.getPropertyInfo().getConstructionPrice());
//		pi.setPriceProofs(propertyDocuments.getBytes());
//		pi.setPropertyDocuments(priceProofs.getBytes());
		
		PropertyAddress pa = savedCustomer.getPropertyInfo().getPropertyAddress();
		
		pa.setAreaName(savedCustomer.getPropertyInfo().getPropertyAddress().getAreaName());
		pa.setCityName(savedCustomer.getPropertyInfo().getPropertyAddress().getCityName());
		pa.setDistrict(savedCustomer.getPropertyInfo().getPropertyAddress().getDistrict());
		pa.setState(savedCustomer.getPropertyInfo().getPropertyAddress().getState());
		pa.setPincode(savedCustomer.getPropertyInfo().getPropertyAddress().getPincode());
		pa.setStreetName(savedCustomer.getPropertyInfo().getPropertyAddress().getStreetName());

		pi.setPropertyAddress(pa);
		
		c.setPropertyInfo(pi);
		
		GurantorDetails gd = savedCustomer.getGurantorDetails();
		gd.setGurantorName(savedCustomer.getGurantorDetails().getGurantorName());
		gd.setGurantorDateOfBirth(savedCustomer.getGurantorDetails().getGurantorDateOfBirth());
		gd.setGurantorMobileNumber(savedCustomer.getGurantorDetails().getGurantorMobileNumber());
		gd.setGurantorAadharCardNo(savedCustomer.getGurantorDetails().getGurantorAadharCardNo());
		gd.setGurantorAddress(savedCustomer.getGurantorDetails().getGurantorAddress());
		gd.setGurantorRelationship(savedCustomer.getGurantorDetails().getGurantorRelationship());
		
		c.setGurantorDetails(gd);
		
		
		//need to set and get all fields of customer, from savedCustomer to Customer c
		
		
		
		Customer cla = om.readValue(customerApplication, Customer.class);
		
	
		DeligenceReport dr = savedCustomer.getDeligenceReport();
		
		dr.setDeligenceReportId(savedCustomer.getDeligenceReport().getDeligenceReportId());
	
		FieldInvestigation fin = savedCustomer.getDeligenceReport().getFieldInvestigation();
		fin.setAddressValidity(cla.getDeligenceReport().getFieldInvestigation().getAddressValidity());
		fin.setContactDetailsValidity(cla.getDeligenceReport().getFieldInvestigation().getContactDetailsValidity());
		fin.setCompanyDetailsValidity(cla.getDeligenceReport().getFieldInvestigation().getCompanyDetailsValidity());
		fin.setPropertyLegality(cla.getDeligenceReport().getFieldInvestigation().getPropertyLegality());	
		dr.setFieldInvestigation(fin);
		
		FinancialCheck fc = savedCustomer.getDeligenceReport().getFinancialCheck();
		fc.setCibilScore(cla.getDeligenceReport().getFinancialCheck().getCibilScore());
		fc.setNetIncome(cla.getDeligenceReport().getFinancialCheck().getNetIncome());
		dr.setFinancialCheck(fc);
		
		TechnicalCheck tc = savedCustomer.getDeligenceReport().getTechnicalCheck();
		tc.setPropertyVisit(cla.getDeligenceReport().getTechnicalCheck().getPropertyVisit());
		tc.setPropertyValuation(cla.getDeligenceReport().getTechnicalCheck().getPropertyValuation());
		dr.setTechnicalCheck(tc);
		
		c.setDeligenceReport(dr);
		c.setDeligenceReportStatus(cla.getDeligenceReportStatus());
	
		hlsi.insertStep5Data(c);
		
	return "Step5 saved";
		
	}
	
}
