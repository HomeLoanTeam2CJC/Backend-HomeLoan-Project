package com.hexaware.hlmbackend.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DefaulterList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer defaulterId;
	private String applicantName;
	private Integer contactDetail;
	private String address;
	private Integer lastContactDate;
	private String customerResponse;
}
