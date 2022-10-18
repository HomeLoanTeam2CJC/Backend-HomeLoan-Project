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
public class DeligenceReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deligenceReportId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private FinancialCheck financialCheck;
	
	@OneToOne(cascade = CascadeType.ALL)
	private FieldInvestigation fieldInvestigation;
	
	@OneToOne(cascade = CascadeType.ALL)
	private TechnicalCheck technicalCheck;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "deligenceReport")
	private Customer customer;
	
}
