package com.hexaware.hlmbackend.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class FinancialCheck {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer financialCheckId;
	private Integer cibilScore;
	private  Double netIncome;
	
}
