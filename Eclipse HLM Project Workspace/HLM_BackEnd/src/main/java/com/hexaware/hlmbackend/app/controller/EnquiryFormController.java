package com.hexaware.hlmbackend.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexaware.hlmbackend.app.model.Address;
import com.hexaware.hlmbackend.app.model.CibilData;
import com.hexaware.hlmbackend.app.model.EnquiryForm;
import com.hexaware.hlmbackend.app.serviceimpl.HomeLoanServiceImpl;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@CrossOrigin("*")
@RestController
@RequestMapping("/enquiryApi")
public class EnquiryFormController {
	
	@Autowired
	HomeLoanServiceInterface  hlsi;	
	
	@PostMapping(value = "/postEnquriyForm", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public EnquiryForm PostEnquiryFormData(
			@RequestPart String userEnquiryForm,
			@RequestPart MultipartFile uploadedPancard) throws IOException
	{
		
		
		ObjectMapper om = new ObjectMapper(); //studentName is in json format, so converting it into java Student format
		EnquiryForm ef = om.readValue(userEnquiryForm, EnquiryForm.class);
		
		EnquiryForm eqf = new EnquiryForm();
		Address addr = new Address();
		addr.setStreetName(ef.getAddress().getStreetName());
		addr.setHouseNumber(ef.getAddress().getHouseNumber());
		addr.setDistrict(ef.getAddress().getDistrict());
		addr.setCityName(ef.getAddress().getCityName());
		addr.setAreaName(ef.getAddress().getAreaName());
		addr.setState(ef.getAddress().getState());
		addr.setPincode(ef.getAddress().getPincode());
		
		eqf.setFirstName(ef.getFirstName());
		eqf.setLastName(ef.getLastName());
		eqf.setDateOfBirth(ef.getDateOfBirth());
		eqf.setEmail(ef.getEmail());
		eqf.setGender(ef.getGender());
		eqf.setMobileno(ef.getMobileno());
		eqf.setPanno(ef.getPanno());
		eqf.setOccupation(ef.getOccupation());
		eqf.setAddress(addr);
		eqf.setLoanPurpose(ef.getLoanPurpose());
		eqf.setNearestBranch(ef.getNearestBranch());
		
		eqf.setUploadedPancard(uploadedPancard.getBytes());
		
		EnquiryForm savedEnquiryForm=hlsi.PostEnquiryFormData(eqf);
		return savedEnquiryForm;
	}
	
	@GetMapping("/getallenquirydata")
	public List<EnquiryForm> getEnquiryFormData(){
		
		List<EnquiryForm> lenqiry=hlsi.getEnquiryFormData();
		
		return lenqiry;
	}
	
//	@PutMapping("/updateenquirydata/{enquiryId}")
//	public EnquiryForm updateEnquiryFormData(@RequestPart String eqi ,@PathVariable Integer enquiryId) {
//		
//		
//		EnquiryForm ef= hlsi.updateEnquiryFormData(eqi,enquiryId);
//		return null;
//		
//	}
	
	
	
	
	
	
	@PutMapping(value = "/updateEnquriyForm/{enquiryId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public EnquiryForm updateEnquiryFormData(
			@RequestPart String userEnquiryForm,
			@PathVariable Integer enquiryId) throws IOException
	{
//		@RequestPart("uploadedPancard") MultipartFile uploadedPancard,
		
		
		System.out.println("Compiler reached inside update method");
		
		ObjectMapper om = new ObjectMapper(); //studentName is in json format, so converting it into java Student format
		EnquiryForm ef = om.readValue(userEnquiryForm, EnquiryForm.class);
		
		EnquiryForm eqf = new EnquiryForm();
		
		Address addr = new Address();
		
		//Address id needs to be set otherwise new separate object of Address will be created
		System.out.println(ef.getAddress().getAddressId());
		addr.setAddressId(ef.getAddress().getAddressId());
		
		addr.setStreetName(ef.getAddress().getStreetName());
		addr.setHouseNumber(ef.getAddress().getHouseNumber());
		addr.setDistrict(ef.getAddress().getDistrict());
		addr.setCityName(ef.getAddress().getCityName());
		addr.setAreaName(ef.getAddress().getAreaName());
		addr.setState(ef.getAddress().getState());
		addr.setPincode(ef.getAddress().getPincode());
		
		
		
//		Integer enquiryIdInteger = Integer.parseInt(enquiryId);
//		System.out.println();
		//In order to perform update operation using save method
		eqf.setEnquiryId(enquiryId);
		
		eqf.setFirstName(ef.getFirstName());
		eqf.setLastName(ef.getLastName());
		eqf.setDateOfBirth(ef.getDateOfBirth());
		eqf.setEmail(ef.getEmail());
		eqf.setGender(ef.getGender());
		eqf.setMobileno(ef.getMobileno());
		eqf.setPanno(ef.getPanno());
		eqf.setOccupation(ef.getOccupation());
		eqf.setAddress(addr);
		eqf.setLoanPurpose(ef.getLoanPurpose());
		eqf.setNearestBranch(ef.getNearestBranch());
		
		
		//To get pancard file form DB and reassign it
		EnquiryForm enquiryForPancard = hlsi.getPancard(enquiryId);
		eqf.setUploadedPancard(enquiryForPancard.getUploadedPancard());
		
		CibilData cibil = new CibilData();
		
		if(ef.getCibilDetails().getCibilId() != null) {
			
			System.out.println("If block executed");
			cibil.setCibilId(ef.getCibilDetails().getCibilId());
			cibil.setCibilScore(ef.getCibilDetails().getCibilScore());
			cibil.setCibilScoreDateTime(ef.getCibilDetails().getCibilScoreDateTime());
			cibil.setStatus(ef.getCibilDetails().getStatus());
			cibil.setRemarksByOe(ef.getCibilDetails().getRemarksByOe());
		}
		else {
			
			System.out.println("else block executed");
			
			cibil.setCibilScore(ef.getCibilDetails().getCibilScore());
			cibil.setCibilScoreDateTime(ef.getCibilDetails().getCibilScoreDateTime());
			cibil.setStatus(ef.getCibilDetails().getStatus());
			cibil.setRemarksByOe(ef.getCibilDetails().getRemarksByOe());
			
		}
		
		
		
		eqf.setCibilDetails(cibil);
		
		EnquiryForm savedEnquiryForm=hlsi.updateEnquiryFormData(eqf);
		return savedEnquiryForm;
	}
 
}
