package com.hexaware.hlmbackend.app.serviceinterface;

import java.util.List;

import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.model.EnquiryForm;

public interface HomeLoanServiceInterface {

	EnquiryForm PostEnquiryFormData(EnquiryForm eqi);

	List<EnquiryForm> getEnquiryFormData();

	EnquiryForm updateEnquiryFormData(EnquiryForm eqf);

	String insertCustomerApplication(Customer c);

	

}