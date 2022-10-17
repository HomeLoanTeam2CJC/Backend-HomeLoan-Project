package com.hexaware.hlmbackend.app.serviceinterface;

import java.util.List;

import com.hexaware.hlmbackend.app.model.Address;
import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.model.EnquiryForm;
import com.hexaware.hlmbackend.app.model.FamilyInfo;
import com.hexaware.hlmbackend.app.model.Profession;

public interface HomeLoanServiceInterface {

	EnquiryForm PostEnquiryFormData(EnquiryForm eqi);

	List<EnquiryForm> getEnquiryFormData();

	EnquiryForm updateEnquiryFormData(EnquiryForm eqf);

	String insertCustomerApplication(Customer c);

	void insertCustomerAddress(Address addr1);

	void insertCustomerFamilyInfo(FamilyInfo fi);

	void insertCustomerProffesion(Profession p);

	

}