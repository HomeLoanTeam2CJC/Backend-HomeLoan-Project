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
public class Profession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer professionId;
	private String professionType;
	private Double professionSalary;
	private String professionDesignation;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "profession")
	private Customer customer;
}
