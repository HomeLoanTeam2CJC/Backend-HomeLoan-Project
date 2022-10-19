package com.hexaware.hlmbackend.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	private Integer houseNumber;
	private String streetName;
	private String areaName;
	private String cityName;
	private String district;
	private String state;
	private Integer pincode;
	
//	@OneToOne(cascade = CascadeType.ALL,mappedBy = "customerAddress")
//	private Customer customer;
}
