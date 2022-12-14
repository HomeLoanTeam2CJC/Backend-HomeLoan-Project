package com.hexaware.hlmbackend.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class AllPersonalDocuments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer documentId;
	@Lob
	private byte[] addressProof;
	@Lob
	private byte[] panCard;
	@Lob
	private byte[] incomeTax;
	@Lob
	private byte[] aadharCard;
	@Lob
	private byte[] photo;
	@Lob
	private byte[] signature;
	@Lob
	private byte[] thumbPrint;
	@Lob
	private byte[] bankCheque;
	@Lob
	private byte[] salarySlips;
	
//	@OneToOne(cascade = CascadeType.ALL, mappedBy = "allPersonalDocuments")
//	private Customer customer;
	
}
