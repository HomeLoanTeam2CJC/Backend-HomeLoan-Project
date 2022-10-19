package com.hexaware.hlmbackend.app.serviceinterface;

import java.util.List;

import com.hexaware.hlmbackend.app.model.AccountDetails;
import com.hexaware.hlmbackend.app.model.Address;
import com.hexaware.hlmbackend.app.model.AllPersonalDocuments;
import com.hexaware.hlmbackend.app.model.CibilData;
import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.model.EducationalInfo;
import com.hexaware.hlmbackend.app.model.EnquiryForm;
import com.hexaware.hlmbackend.app.model.FamilyInfo;
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

	String insertStep6Data(Customer c);

	String insertStep7Data(Customer c);

	String insertStep8Data(Customer c);

	String insertStep9Data(Customer c);

	String insertstep10Data(Customer c);



	

	//void insertCustomerStep5Data(Customer c);

	

	

	

}