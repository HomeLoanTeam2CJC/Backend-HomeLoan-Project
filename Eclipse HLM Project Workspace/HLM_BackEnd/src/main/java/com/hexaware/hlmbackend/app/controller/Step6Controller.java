package com.hexaware.hlmbackend.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@CrossOrigin("*")
@RequestMapping("/step6api")
public class Step6Controller {
	
	@PostMapping(value = "/PostStep6api")
	public String InsertStep6Data(@RequestPart String customerApplication) throws JsonMappingException, JsonProcessingException
	{
		return null;
	}
}
