package com.hexaware.hlmbackend.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TechnicalCheck {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer technicalCheckId;
	private String propertyVisit;
	private Double propertyValuation;
}
