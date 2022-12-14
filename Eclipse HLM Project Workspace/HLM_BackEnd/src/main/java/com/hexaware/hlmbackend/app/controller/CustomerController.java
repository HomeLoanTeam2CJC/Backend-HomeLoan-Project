package com.hexaware.hlmbackend.app.controller;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.type.descriptor.sql.JdbcTypeFamilyInformation.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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
import com.hexaware.hlmbackend.app.model.AccountDetails;
import com.hexaware.hlmbackend.app.model.Address;
import com.hexaware.hlmbackend.app.model.AllPersonalDocuments;
import com.hexaware.hlmbackend.app.model.CibilData;
import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.model.CustomerEmail;
import com.hexaware.hlmbackend.app.model.DeligenceReport;
import com.hexaware.hlmbackend.app.model.EducationalInfo;
import com.hexaware.hlmbackend.app.model.EnquiryForm;
import com.hexaware.hlmbackend.app.model.FamilyInfo;
import com.hexaware.hlmbackend.app.model.FieldInvestigation;
import com.hexaware.hlmbackend.app.model.FinancialCheck;
import com.hexaware.hlmbackend.app.model.GurantorDetails;
import com.hexaware.hlmbackend.app.model.LoanAgreement;
import com.hexaware.hlmbackend.app.model.LoanDisbursement;
import com.hexaware.hlmbackend.app.model.Profession;
import com.hexaware.hlmbackend.app.model.PropertyAddress;
import com.hexaware.hlmbackend.app.model.PropertyInfo;
import com.hexaware.hlmbackend.app.model.SanctionLetter;
import com.hexaware.hlmbackend.app.model.TechnicalCheck;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;


@RestController
@CrossOrigin("*")
@RequestMapping("/customerApi")
public class CustomerController {
	
	
	@Autowired
	private HomeLoanServiceInterface hlsi;
	
	
	@Value("${spring.mail.username}")
	private String sender;
	
	@PostMapping(value = "/newCustomerApplication", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
		
		c.setDeligenceReportStatus(cla.getDeligenceReportStatus());
		c.setDoReportBmResponseStatus(cla.getDoReportBmResponseStatus());
		c.setDoReportBmResponse(cla.getDoReportBmResponse());
		c.setSanctionLetterStatus(cla.getSanctionLetterStatus());
		c.setCustomerAcceptanceStatus(cla.getCustomerAcceptanceStatus());
		c.setLoanAgreementStatus(cla.getLoanAgreementStatus());
		c.setLoanAgreementBmSignStatus(cla.getLoanAgreementBmSignStatus());
//		c.setLoanAgreementCustomerSignStatus(cla.getLoanAgreementCustomerSignStatus());
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
		paddr.setStreetName(cla.getPropertyInfo().getPropertyAddress().getStreetName());
		paddr.setAreaName(cla.getPropertyInfo().getPropertyAddress().getAreaName());
		paddr.setCityName(cla.getPropertyInfo().getPropertyAddress().getCityName());
		paddr.setDistrict(cla.getPropertyInfo().getPropertyAddress().getDistrict());
		paddr.setState(cla.getPropertyInfo().getPropertyAddress().getState());
		paddr.setPincode(cla.getPropertyInfo().getPropertyAddress().getPincode());
		
		pi.setPropertyAddress(paddr);
		c.setPropertyInfo(pi);
		
		GurantorDetails gd = new GurantorDetails();
		gd.setGurantorName(cla.getGurantorDetails().getGurantorName());
		gd.setGurantorDateOfBirth(cla.getGurantorDetails().getGurantorDateOfBirth());
		gd.setGurantorRelationship(cla.getGurantorDetails().getGurantorRelationship());
		gd.setGurantorMobileNumber(cla.getGurantorDetails().getGurantorMobileNumber());
		gd.setGurantorAadharCardNo(cla.getGurantorDetails().getGurantorAadharCardNo());
		gd.setGurantorAddress(cla.getGurantorDetails().getGurantorAddress());
		
		c.setGurantorDetails(gd);
		
		DeligenceReport dr = new DeligenceReport();
		dr.setFieldInvestigation(cla.getDeligenceReport().getFieldInvestigation());
		
		FieldInvestigation fin=new FieldInvestigation();
		fin.setAddressValidity(cla.getDeligenceReport().getFieldInvestigation().getAddressValidity());
		fin.setContactDetailsValidity(cla.getDeligenceReport().getFieldInvestigation().getContactDetailsValidity());
		fin.setCompanyDetailsValidity(cla.getDeligenceReport().getFieldInvestigation().getCompanyDetailsValidity());
		fin.setPropertyLegality(cla.getDeligenceReport().getFieldInvestigation().getPropertyLegality());	
		
		dr.setFinancialCheck(cla.getDeligenceReport().getFinancialCheck());
		
		FinancialCheck fc=new FinancialCheck();
		fc.setCibilScore(cla.getDeligenceReport().getFinancialCheck().getCibilScore());
		fc.setNetIncome(cla.getDeligenceReport().getFinancialCheck().getNetIncome());
		
		dr.setTechnicalCheck(cla.getDeligenceReport().getTechnicalCheck());
		
		TechnicalCheck tc=new TechnicalCheck();
		tc.setPropertyVisit(cla.getDeligenceReport().getTechnicalCheck().getPropertyVisit());
		tc.setPropertyValuation(cla.getDeligenceReport().getTechnicalCheck().getPropertyValuation());
		
		c.setDeligenceReport(dr);
		
		SanctionLetter sl = new SanctionLetter();
		sl.setSanctionDate(cla.getSanctionLetter().getSanctionDate());
		sl.setApplicantName(cla.getSanctionLetter().getApplicantName());
		sl.setContactDetails(cla.getSanctionLetter().getContactDetails());
		sl.setMaxSanctionAmount(cla.getSanctionLetter().getMaxSanctionAmount());
		sl.setMaxEmi(cla.getSanctionLetter().getMaxEmi());
		sl.setAverageTenure(cla.getSanctionLetter().getAverageTenure());
		sl.setValidity(cla.getSanctionLetter().getValidity());
		
		c.setSanctionLetter(sl);
		
		LoanAgreement la = new LoanAgreement();
		la.setLoanAgreementName(cla.getLoanAgreement().getApplicantName());
		la.setApplicantName(cla.getLoanAgreement().getApplicantName());
		la.setContactDetails(cla.getLoanAgreement().getContactDetails());
		la.setLoanAmountSanctioned(cla.getLoanAgreement().getLoanAmountSanctioned());
		la.setInterestType(cla.getLoanAgreement().getInterestType());
		la.setRateOfInterest(cla.getLoanAgreement().getRateOfInterest());
		la.setLoanTenure(cla.getLoanAgreement().getLoanTenure());
		la.setMonthlyEmiAmount(cla.getLoanAgreement().getMonthlyEmiAmount());
		la.setModeOfPayment(cla.getLoanAgreement().getModeOfPayment());
		la.setRemarks(cla.getLoanAgreement().getRemarks());
		la.setStatus(cla.getLoanAgreement().getStatus());
		
		c.setLoanAgreement(la);
		
		LoanDisbursement ld = new LoanDisbursement();
		ld.setLoanNumber(cla.getLoanDisbursement().getLoanNumber());
		ld.setAgreementDate(cla.getLoanDisbursement().getAgreementDate());
		ld.setAmountPayType(cla.getLoanDisbursement().getAmountPayType());
		ld.setTotalLoanAmount(cla.getLoanDisbursement().getTotalLoanAmount());
		ld.setBankName(cla.getLoanDisbursement().getBankName());
		ld.setAccountNumber(cla.getLoanDisbursement().getAccountNumber());
		ld.setIfscCode(cla.getLoanDisbursement().getIfscCode());
		ld.setAccountType(cla.getLoanDisbursement().getAccountType());
		ld.setTransferAmount(cla.getLoanDisbursement().getTransferAmount());
		ld.setPaymentStatus(cla.getLoanDisbursement().getPaymentStatus());
		ld.setAmountPaidDate(cla.getLoanDisbursement().getAmountPaidDate());
		
		c.setLoanDisbursement(ld);
		
		String msg=hlsi.insertCustomerApplication(c);
		
		return msg;
	}

	@PostMapping("/postStep1/{savedCustomerId}")
	public String saveStep1(
			@RequestPart String customerApplication,
			@PathVariable Integer savedCustomerId) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper om = new ObjectMapper(); 
		Customer cla = om.readValue(customerApplication, Customer.class);
		Customer c = new Customer();
		c.setCustomerId(savedCustomerId);
		
		c.setCustomerName(cla.getCustomerName());
		c.setCustomerDateOfBirth(cla.getCustomerDateOfBirth());
		c.setCustomerAge(cla.getCustomerAge());
		c.setCustomerGender(cla.getCustomerGender());
		c.setCustomerEmail(cla.getCustomerEmail());
		c.setCustomerMobileNumber(cla.getCustomerMobileNumber());
		c.setCustomerMobileNumber2(cla.getCustomerMobileNumber2());
		c.setCustomerAmountPaidForHome(cla.getCustomerAmountPaidForHome());
		c.setCustomerTotalLoanRequired(cla.getCustomerTotalLoanRequired());
		
		Address addr1 = new Address();
		addr1.setHouseNumber(cla.getCustomerAddress().getHouseNumber());
		addr1.setStreetName(cla.getCustomerAddress().getStreetName());
		addr1.setAreaName(cla.getCustomerAddress().getAreaName());
		addr1.setCityName(cla.getCustomerAddress().getCityName());
		addr1.setDistrict(cla.getCustomerAddress().getDistrict());
		addr1.setState(cla.getCustomerAddress().getState());
		addr1.setPincode(cla.getCustomerAddress().getPincode());
		
		c.setCustomerAddress(addr1);
		
		
		FamilyInfo fi = new FamilyInfo();
		c.setFamilyInfo(fi);
		
		EducationalInfo ei = new EducationalInfo();
		c.setEducationalInfo(ei);
		
		Profession pf = new Profession();
		c.setProfession(pf);
		
		AllPersonalDocuments apd = new AllPersonalDocuments();
		c.setAllPersonalDocuments(apd);
		
		CibilData cd = new CibilData();
		c.setCibilDetails(cd);
		
		AccountDetails ad = new AccountDetails();
		c.setAccountDetails(ad);
		
		PropertyInfo pi = new PropertyInfo();
		PropertyAddress pa = new PropertyAddress();
		pi.setPropertyAddress(pa);
		c.setPropertyInfo(pi);
		
		GurantorDetails gd = new GurantorDetails();
		c.setGurantorDetails(gd);
		
		DeligenceReport dr = new DeligenceReport();
		FinancialCheck fc = new FinancialCheck();
		dr.setFinancialCheck(fc);
		FieldInvestigation fin = new FieldInvestigation();
		dr.setFieldInvestigation(fin);
		TechnicalCheck tc = new TechnicalCheck();
		dr.setTechnicalCheck(tc);
		c.setDeligenceReport(dr);
		
		
		
		SanctionLetter sl = new SanctionLetter();
		c.setSanctionLetter(sl);
		
		LoanAgreement la = new LoanAgreement();
		c.setLoanAgreement(la);
		
		LoanDisbursement ld = new LoanDisbursement();
		c.setLoanDisbursement(ld);
		

		
		hlsi.insertCustomerApplication(c);
	
		return "Step1 Scucccessfull";
	}
	
	
	@GetMapping("/getCustomerList")
	public List<Customer> getCustomerList(){
		
		List<Customer> customerList = hlsi.getCustomerList();
		
		for(Customer c : customerList) {
			System.out.println(c.getCustomerName());
			System.out.println(c.getCustomerId());
		}
		return customerList;
	}
	
	@GetMapping("/getCustomer/{customerId}")
	public Customer getCustomer(@PathVariable Integer customerId){
		Customer c = hlsi.getSavedCustomer(customerId);
		return c;
	}
	
	
	@RequestMapping(value = "/customerEmail/{savedCustomerId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String sendCustomerEmail(
			@RequestPart MultipartFile attachedFile,
			@RequestPart String customerEmail,
			@PathVariable Integer savedCustomerId) throws IOException, MessagingException {
		
		Customer savedCustomer = hlsi.getSavedCustomer(savedCustomerId);
		
		ObjectMapper om = new ObjectMapper();
		CustomerEmail tempCustEmail = om.readValue(customerEmail, CustomerEmail.class);
		
		CustomerEmail custEmail = new CustomerEmail();
		custEmail.setFromSender(sender);
		custEmail.setToReceiver(savedCustomer.getCustomerEmail());
		custEmail.setSubject(tempCustEmail.getSubject());
		custEmail.setTextBody(tempCustEmail.getTextBody());
		custEmail.setAttachment(attachedFile.getBytes());
		
		hlsi.sendEmail(custEmail, attachedFile);
		
		
		return "Email Sent Succesfully";	
	}
	
	
	
}
