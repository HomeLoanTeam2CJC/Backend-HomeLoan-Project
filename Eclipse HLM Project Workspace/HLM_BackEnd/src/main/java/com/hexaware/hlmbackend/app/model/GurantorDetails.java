package com.hexaware.hlmbackend.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class GurantorDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer gurantorId;
	private String gurantorName;
	private String gurantorDateOfBirth;
	private String gurantorrelationship;
	private Long gurantorMobileNo;
	private Long gurantorAadharCardNo;
	private String gurantorAddress;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "propertyInfo")
	private Customer customer;
}
