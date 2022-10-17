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
	
<<<<<<< HEAD
=======
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "deligenceReport")
	private Customer customer;
	
>>>>>>> 9d53edb73bfe33e5b411011bce97a874d1920ce6
}
