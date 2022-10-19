package com.hexaware.hlmbackend.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hlmbackend.app.model.Ledger;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@CrossOrigin("*")
@RestController
@RequestMapping("/ledgerApi")
public class LedgerController {
	@Autowired
	HomeLoanServiceInterface  hlsi;
	
	
	@PostMapping("/postLedgerForm")
	public Ledger savedLedgerData(@RequestPart Ledger ledgerId) {
	Ledger ledger	=hlsi.savedLedgerData(ledgerId);
		return ledger;
	}
	
	@GetMapping("/getLedgerForm/{ledgerId}")
	public Ledger getLedgerData(@PathVariable Integer ledgerId)
	{
	Ledger ledger=	hlsi.getLedgerData(ledgerId);
		return ledger;
	}
	
	
}
