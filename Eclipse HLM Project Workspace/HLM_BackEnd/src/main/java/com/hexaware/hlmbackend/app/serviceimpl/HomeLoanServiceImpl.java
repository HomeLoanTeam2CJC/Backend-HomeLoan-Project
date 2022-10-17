package com.hexaware.hlmbackend.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hlmbackend.app.model.Address;
import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.model.EducationalInfo;
import com.hexaware.hlmbackend.app.model.EnquiryForm;
import com.hexaware.hlmbackend.app.model.FamilyInfo;
import com.hexaware.hlmbackend.app.model.Profession;
import com.hexaware.hlmbackend.app.repository.AddressRepository;
import com.hexaware.hlmbackend.app.repository.CustomerRepository;
import com.hexaware.hlmbackend.app.repository.EducationInfoRepository;
import com.hexaware.hlmbackend.app.repository.EnquiryFormRepository;
import com.hexaware.hlmbackend.app.repository.FamilyInfoRepository;
import com.hexaware.hlmbackend.app.repository.ProfessionRepository;
import com.hexaware.hlmbackend.app.serviceinterface.HomeLoanServiceInterface;

@Service
public class HomeLoanServiceImpl implements HomeLoanServiceInterface{
	
	
	@Autowired
	private EnquiryFormRepository enquiryRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
//	@Autowired
//	private AddressRepository addressRepo;
	
	@Autowired
	private FamilyInfoRepository familyRepo;
	
	@Autowired
	private ProfessionRepository professionRepo;
	
	@Autowired
	private EducationInfoRepository educationRepo;

	@Override
	public EnquiryForm PostEnquiryFormData(EnquiryForm eqi) {
		
		return enquiryRepo.save(eqi);
	}

	@Override
	public List<EnquiryForm> getEnquiryFormData() {
		List<EnquiryForm> lenquiry=enquiryRepo.findAll();
		return lenquiry;
	}

	@Override
	public EnquiryForm updateEnquiryFormData(EnquiryForm eqf) {
		
		//updating using same save method
		
		return enquiryRepo.save(eqf);
	}

	@Override
	public String insertCustomerApplication(Customer c) {
		
		Customer c1 = customerRepo.save(c);
		if(c1!=null) {
			return "Data Saved";
		}else {
			return "Failed to Insert";
		}
	}

//	@Override
//	public void insertCustomerAddress(Address addr1) {
//		
//		addressRepo.save(addr1);
//		
//	}

	@Override
	public void insertCustomerFamilyInfo(FamilyInfo fi) {
		
		familyRepo.save(fi);
		
	}

	@Override
	public void insertCustomerProffesion(Profession p) {
		
		professionRepo.save(p);
	}

	@Override
	public void insertCustomerEducation(EducationalInfo ei) {
	
		educationRepo.save(ei);
	}

	@Override
	public EnquiryForm getPancard(Integer enquiryId) {
		
		Optional<EnquiryForm> enquiryForPnacrd = enquiryRepo.findById(enquiryId);
		if(enquiryForPnacrd.isPresent()) {
			return enquiryForPnacrd.get();
			}
		else {
			return null;
		}
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
