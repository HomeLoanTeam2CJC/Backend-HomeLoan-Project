package com.hexaware.hlmbackend.app.model;

import javax.persistence.OneToOne;

public class DeligenceReport {

	private Integer deligenceReportId;
	@OneToOne
	private FinancialCheck financialCheck;
	@OneToOne
	private FieldInvestigation fieldInvestigation;
	@OneToOne
	private TechnicalCheck technicalCheck;
}
