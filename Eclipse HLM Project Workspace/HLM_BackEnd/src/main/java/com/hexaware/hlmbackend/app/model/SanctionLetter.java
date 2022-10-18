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
public class SanctionLetter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sanctionId;
	private String sanctionDate;
	private String applicantName;
	private Integer contactDetails;
	private Integer maxSanctionAmount;
	private Integer maxEmi;
	private Integer averageTenure;
	private Integer validity;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "sanctionLetter")
	private Customer customer;
}
