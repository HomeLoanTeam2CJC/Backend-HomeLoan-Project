package com.hexaware.hlmbackend.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

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
import com.hexaware.hlmbackend.app.model.LoanAgreement;
import com.hexaware.hlmbackend.app.model.Profession;
import com.hexaware.hlmbackend.app.model.PropertyAddress;
import com.hexaware.hlmbackend.app.model.PropertyInfo;
import com.hexaware.hlmbackend.app.model.TechnicalCheck;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@RestController
@CrossOrigin("*")
@RequestMapping("/step10Api")
public class Step10Controller {
	
	@Autowired
	private HomeLoanServiceInterface hlsi;
	
	@PostMapping("/postStep10/{savedCustomerId}")
	public String InsertStep10Data(
			@RequestPart String customerApplication,
			@PathVariable Integer savedCustomerId) throws JsonMappingException, JsonProcessingException
	{
		
		//Converting JSON into POJO
			ObjectMapper om = new ObjectMapper(); 
			Customer cla = om.readValue(customerApplication, Customer.class);
			
			
			
			
		//Fetching customer from Database
			Customer savedCustomer = hlsi.getSavedCustomer(savedCustomerId);
			Customer c = new Customer();
			
			
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
			c.setCustomerAddress(addr1);
			
	//step2
			EducationalInfo ei = savedCustomer.getEducationalInfo();
			c.setEducationalInfo(ei);
			
			FamilyInfo fi = savedCustomer.getFamilyInfo();
			c.setFamilyInfo(fi);
			
			Profession pf = savedCustomer.getProfession();
			c.setProfession(pf);
			
	//step3
			AllPersonalDocuments apd =savedCustomer.getAllPersonalDocuments();
			c.setAllPersonalDocuments(apd);  //need to verify if image files are being reset properly or is it setting it Null
			
	//step4
			CibilData cd = savedCustomer.getCibilDetails();
			c.setCibilDetails(cd);
			
			AccountDetails ad = savedCustomer.getAccountDetails();
			c.setAccountDetails(ad);
			
			PropertyInfo pi = savedCustomer.getPropertyInfo();
			PropertyAddress pa = savedCustomer.getPropertyInfo().getPropertyAddress();
			pi.setPropertyAddress(pa);
			c.setPropertyInfo(pi);
			
			GurantorDetails gd = savedCustomer.getGurantorDetails();
			c.setGurantorDetails(gd);
			
			
	//step5
			DeligenceReport dr = savedCustomer.getDeligenceReport();
			FieldInvestigation fin = savedCustomer.getDeligenceReport().getFieldInvestigation();
			dr.setFieldInvestigation(fin);
			FinancialCheck fc = savedCustomer.getDeligenceReport().getFinancialCheck();
			dr.setFinancialCheck(fc);
			TechnicalCheck tc = savedCustomer.getDeligenceReport().getTechnicalCheck();
			dr.setTechnicalCheck(tc);
			c.setDeligenceReport(dr);
			
			c.setDeligenceReportStatus(savedCustomer.getDeligenceReportStatus());
		
			
	//step5.5
			c.setSanctionLetter(savedCustomer.getSanctionLetter());
			c.setSanctionLetterStatus(savedCustomer.getSanctionLetterStatus());
		
	//step6
			c.setDoReportBmResponseStatus(savedCustomer.getDoReportBmResponseStatus());
			c.setDoReportBmResponse(savedCustomer.getDoReportBmResponse());
		
		
			
			
		
	//step7
			
			System.out.println("Customer Response: "+savedCustomer.getCustomerAcceptanceStatus());
			c.setCustomerAcceptanceStatus(savedCustomer.getCustomerAcceptanceStatus());
			
	//step8
			LoanAgreement la = savedCustomer.getLoanAgreement();
			c.setLoanAgreement(la);
			c.setLoanAgreementStatus(savedCustomer.getLoanAgreementStatus());
			
	//step9		
			c.setLoanAgreementBmSignStatus(savedCustomer.getLoanAgreementBmSignStatus());
			
	//current Step10
			c.setLoanDisbursement(cla.getLoanDisbursement());
			c.setLoanDisbursementStatus(cla.getLoanDisbursementStatus());
			
			hlsi.insertStep9Data(c);
			
			return "Step9 Saved";
	}

}
