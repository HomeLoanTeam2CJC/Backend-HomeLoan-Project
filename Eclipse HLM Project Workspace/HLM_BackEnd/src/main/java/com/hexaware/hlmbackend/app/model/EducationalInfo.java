package com.hexaware.hlmbackend.app.model;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class EducationalInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer educationId;
	private String educationType;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "educationalInfo")
	private Customer customer;
}
