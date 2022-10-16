package com.hexaware.hlmbackend.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class FamilyInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer familyInfoId;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private Integer noOfFamilyMembers;
	private Integer noOfChildren;
	private String maritalStatus;
	private Integer familyIncome;
	
}
