package com.hexaware.hlmbackend.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.hlmbackend.app.model.AccountDetails;
import com.hexaware.hlmbackend.app.model.Address;
import com.hexaware.hlmbackend.app.model.AllPersonalDocuments;
import com.hexaware.hlmbackend.app.model.CibilData;
import com.hexaware.hlmbackend.app.model.Customer;
import com.hexaware.hlmbackend.app.model.CustomerEmail;
import com.hexaware.hlmbackend.app.model.EducationalInfo;
import com.hexaware.hlmbackend.app.model.EnquiryForm;
import com.hexaware.hlmbackend.app.model.FamilyInfo;
import com.hexaware.hlmbackend.app.model.GurantorDetails;
import com.hexaware.hlmbackend.app.model.Ledger;
import com.hexaware.hlmbackend.app.model.Profession;
import com.hexaware.hlmbackend.app.model.PropertyInfo;
import com.hexaware.hlmbackend.app.model.SanctionLetter;
import com.hexaware.hlmbackend.app.repository.AccountDetailsRepository;
import com.hexaware.hlmbackend.app.repository.AddressRepository;
import com.hexaware.hlmbackend.app.repository.CibilDataRepository;
import com.hexaware.hlmbackend.app.repository.CustomerRepository;
import com.hexaware.hlmbackend.app.repository.EducationInfoRepository;
import com.hexaware.hlmbackend.app.repository.EmailRepository;
import com.hexaware.hlmbackend.app.repository.EnquiryFormRepository;
import com.hexaware.hlmbackend.app.repository.FamilyInfoRepository;
import com.hexaware.hlmbackend.app.repository.GurantorRepository;
import com.hexaware.hlmbackend.app.repository.LedgerRepository;
import com.hexaware.hlmbackend.app.repository.ProfessionRepository;
import com.hexaware.hlmbackend.app.repository.PropertyInfoRepository;
import com.hexaware.hlmbackend.app.repository.documentsUploadRepository;
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
	
	@Autowired
	private CibilDataRepository cibilRepo;
	
	@Autowired
	private AccountDetailsRepository accountDetailRepo;
	
	@Autowired
	private documentsUploadRepository documentsRepo;
	
	@Autowired
	private PropertyInfoRepository propertyRepo;
	
	@Autowired
	private GurantorRepository gurantorRepo;
	
	@Autowired
	private LedgerRepository ledgerRepo;  
	
	@Autowired
	private JavaMailSender jms;
	
	@Autowired
	private EmailRepository emailRepo;

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

	
	//To get enquiryObject from DB and extract Pancard file from it
	@Override
	public EnquiryForm getPancard(Integer enquiryId) {
		
		Optional<EnquiryForm> enquiryForPancard = enquiryRepo.findById(enquiryId);
		if(enquiryForPancard.isPresent()) {
			return enquiryForPancard.get();
			}
		else {
			return null;
		}
		}

	@Override
	public void insertCibilData(CibilData cd) {
		
		cibilRepo.save(cd);
		
	}

	@Override
	public void insertAccountDetails(AccountDetails ad) {
		
		accountDetailRepo.save(ad);
		
	}

	@Override
	public void documentsUpload(AllPersonalDocuments apd) {
		
		documentsRepo.save(apd);
		
	}

	@Override
	public void insertPropertyInfo(PropertyInfo pi) {
		
		propertyRepo.save(pi);
		
	}


	@Override
	public Customer getSavedCustomer(Integer savedCustomerId) {
		
		Optional<Customer> fetchedCustomer = customerRepo.findById(savedCustomerId);
		
		if(fetchedCustomer.isPresent()) {
			return fetchedCustomer.get();
		}
		else {
		return null;
		}
	}

	public String insertStep5Data(Customer c) {
		
		Customer c1 = customerRepo.save(c);
		if(c1!=null) {
			return "Data Saved";
		}else {
			return "Failed to Insert";
		}
		
	}

	@Override
	public String insertStep6Data(Customer c) {
		
		Customer c1 = customerRepo.save(c);		
		if(c1!=null) {
			return "Data Saved";
		}else {
			return "Failed to Insert";
		}
		
	}

	@Override
	public String insertStep5Point5Data(Customer c) {
		
		Customer c1 = customerRepo.save(c);		
		if(c1!=null) {
			return "Data Saved";
		}else {
			return "Failed to Insert";
		}
	}

	@Override
	public String insertStep7Data(Customer c) {
		

		Customer c1 = customerRepo.save(c);		
		if(c1!=null) {
			return "Data Saved";
		}else {
			return "Failed to Insert";
		}
	
	}

	@Override
	public String insertStep9Data(Customer c) {
		Customer c1 = customerRepo.save(c);		
		if(c1!=null) {
			return "Data Saved";
		}else {
			return "Failed to Insert";
		}
	}

	@Override
	public String insertstep10Data(Customer c) {
		
		Customer c1 = customerRepo.save(c);		
		if(c1!=null) {
			return "Data Saved";
		}else {
			return "Failed to Insert";
		}
	}
	

	@Override
	public List<Customer> getCustomerList() {
		
		List<Customer> customerList = customerRepo.findAll();
		
		return customerList;
	}

	@Override
	public void insertGurantor(GurantorDetails gd) {
		
		gurantorRepo.save(gd);
		
	}

	@Override
	public Ledger savedLedgerData(Ledger ledgerId) {
		return ledgerRepo.save(ledgerId);
		
	}

	@Override
	public Ledger getLedgerData(Integer ledgerId) {
		Optional<Ledger>  ledger=ledgerRepo.findById(ledgerId);
		if(ledger.isPresent())
		{
			return ledger.get();
		}
		else {
			return null;
		}
		
	}

	@Override
	public void sendEmail(CustomerEmail custEmail, MultipartFile attachedFile) throws MessagingException {
		
		MimeMessage mm = jms.createMimeMessage();
		
		MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
		mmh.setFrom(custEmail.getFromSender());
		
		System.out.println("toReceiver: "+custEmail.getToReceiver());
		mmh.setTo(custEmail.getToReceiver());
		mmh.setSubject(custEmail.getSubject());
		
		//Design format of email to be sent
		mmh.setText(
				custEmail.getTextBody()
				);
		mmh.addAttachment(attachedFile.getOriginalFilename(), attachedFile);
		
		emailRepo.save(custEmail);
		
		jms.send(mm);
		
		System.out.println("email sent");
		
		
		
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
	
	
	


