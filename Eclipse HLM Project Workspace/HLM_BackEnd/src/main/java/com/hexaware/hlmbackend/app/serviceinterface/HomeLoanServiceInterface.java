package com.hexaware.hlmbackend.app.serviceinterface;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

import com.hexaware.hlmbackend.app.model.AccountDetails;
import com.hexaware.hlmbackend.app.model.Address;
import com.hexaware.hlmbackend.app.model.AllPersonalDocuments;
import com.hexaware.hlmbackend.app.model.CibilData;
import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.model.CustomerEmail;
import com.hexaware.hlmbackend.app.model.EducationalInfo;
import com.hexaware.hlmbackend.app.model.EnquiryForm;
import com.hexaware.hlmbackend.app.model.FamilyInfo;
import com.hexaware.hlmbackend.app.model.GurantorDetails;
import com.hexaware.hlmbackend.app.model.Ledger;
import com.hexaware.hlmbackend.app.model.Profession;
import com.hexaware.hlmbackend.app.model.PropertyInfo;
import com.hexaware.hlmbackend.app.model.SanctionLetter;

public interface HomeLoanServiceInterface {

	EnquiryForm PostEnquiryFormData(EnquiryForm eqi);

	List<EnquiryForm> getEnquiryFormData();

	EnquiryForm updateEnquiryFormData(EnquiryForm eqf);

	String insertCustomerApplication(Customer c);

//	void insertCustomerAddress(Address addr1);

	void insertCustomerFamilyInfo(FamilyInfo fi);

	void insertCustomerProffesion(Profession p);

	void insertCustomerEducation(EducationalInfo ei);

	EnquiryForm getPancard(Integer enquiryId);

	void insertCibilData(CibilData cd);

	void documentsUpload(AllPersonalDocuments apd);

	void insertAccountDetails(AccountDetails ad);

	void insertPropertyInfo(PropertyInfo pi);

	Customer getSavedCustomer(Integer savedCustomerId);
	
	String insertStep5Data(Customer c);
	
	String insertStep5Point5Data(Customer c);

	String insertStep6Data(Customer c);

	String insertStep7Data(Customer c);

	String insertStep9Data(Customer c);

	String insertstep10Data(Customer c);

	List<Customer> getCustomerList();

	void insertGurantor(GurantorDetails gd);

	Ledger savedLedgerData(Ledger ledgerId);

	Ledger getLedgerData(Integer ledgerId);

	void sendEmail(CustomerEmail custEmail, MultipartFile attachedFile) throws MessagingException ;

	

	

	//void insertCustomerStep5Data(Customer c);

	

	

	

}