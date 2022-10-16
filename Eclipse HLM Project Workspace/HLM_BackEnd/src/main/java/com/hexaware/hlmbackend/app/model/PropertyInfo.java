package com.hexaware.hlmbackend.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class PropertyInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer propertyId;
	private String propertyType;
	private String propertyArea;
	private String constructionArea;
	private Double propertyPrice;
	private Double constructionPrice;
	@Lob
	private byte[] propertyDocuments;
	@Lob
	private byte[] priceProofs;
	
	@OneToOne
	private PropertyAddress propertyAddresss;
	
}
