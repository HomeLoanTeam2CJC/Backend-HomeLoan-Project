package com.hexaware.hlmbackend.app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.hexaware.hlmbackend.app.model.Address;
import com.hexaware.hlmbackend.app.model.CibilData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EnquiryFormDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enquiryId;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String gender;
	private String email;
	private Integer mobileno;
	private String panno;
	private String occupation;
	@OneToOne
	private Address address;
	private boolean existingCustomer;
	private String loanPurpose;
	private String nearestBranch;
	@OneToOne
	private CibilData cibilDetails;

}
