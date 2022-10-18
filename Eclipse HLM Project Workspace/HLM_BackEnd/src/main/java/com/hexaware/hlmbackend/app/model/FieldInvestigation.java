package com.hexaware.hlmbackend.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class FieldInvestigation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer fieldInvestigationId;
	 private String addressValidity;
	 private String contactDetailsValidity;
	 private String companyDetailsValidity;
	 private String propertyLegality;
}
