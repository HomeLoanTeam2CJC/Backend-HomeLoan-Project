package com.hexaware.hlmbackend.app.model;

import javax.persistence.OneToOne;

public class PropertyInfo {
	private Integer propertyId;
	private String propertyType;
	private String propertyArea;
	private String constructionArea;
	private Double propertyPrice;
	private Double constructionPrice;
	private byte[] propertyDocuments;
	private byte[] priceProofs;
	@OneToOne
	private PropertyAddress propertyAddresss;
	
}
