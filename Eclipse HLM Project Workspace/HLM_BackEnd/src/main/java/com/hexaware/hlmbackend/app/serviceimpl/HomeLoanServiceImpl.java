package com.hexaware.hlmbackend.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hlmbackend.app.model.Address;
import com.hexaware.hlmbackend.app.model.EnquiryForm;
import com.hexaware.hlmbackend.app.repository.EnquiryFormRepository;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@Service
public class HomeLoanServiceImpl implements HomeLoanServiceInterface{
	
	
	@Autowired
	EnquiryFormRepository repo;

	@Override
	public EnquiryForm PostEnquiryFormData(EnquiryForm eqi) {
		
		return repo.save(eqi);
	}

	@Override
	public List<EnquiryForm> getEnquiryFormData() {
		List<EnquiryForm> lenquiry=repo.findAll();
		return lenquiry;
	}

	@Override
	public EnquiryForm updateEnquiryFormData(EnquiryForm eqf) {
		
		//updating using same save method
		
		return repo.save(eqf);
	}

	
	
	

//	@Override
//	public EnquiryForm updateEnquiryFormData(EnquiryForm eqi, Integer enquiryId) {
//		
////		Optional<EnquiryForm> op=repo.findById(enquiryId);
////		if(op.isPresent()) {
////			EnquiryForm ef=op.get();
////			Address addr=ef.getAddress();
////			
////			ef.setFirstName(eqi.getFirstName());
////			ef.setLastName(eqi.getLastName());
////			ef.setDateOfBirth(eqi.getDateOfBirth());
////			ef.setGender(eqi.getGender());
////			ef.setEmail(eqi.getEmail());
////			ef.setMobileno(eqi.getMobileno());
////			ef.setPanno(eqi.getPanno());
////			ef.setOccupation(eqi.getOccupation());
////			ef.setAddress(addr);
////			addr.getAddressId();
////			ef.setLoanPurpose(eqi.getLoanPurpose());
////			ef.setNearestBranch(eqi.getNearestBranch());
////			ef.setCibilDetails(eqi.getCibilDetails());
//			//	}
//		return null;
//	}
	
	
	

}
