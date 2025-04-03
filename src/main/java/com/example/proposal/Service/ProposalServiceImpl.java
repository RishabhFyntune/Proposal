package com.example.proposal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proposal.DTO.ProposerDTO;
import com.example.proposal.Repository.ProposalRepo;
import com.example.proposal.model.Proposer;

@Service
public class ProposalServiceImpl implements ProposalService
{

	@Autowired
	private ProposalRepo proposalRepo;
	
	
	
	@Override
	
	 public Proposer register(Proposer proposer) { proposer.setStatus('Y');
	 
	 return proposalRepo.save(proposer); }
	 

	@Override
	public Proposer delete(Long id) throws Exception	
	{
		Optional<Proposer> deleteProposer = proposalRepo.findById(id);
		Proposer updatedeletedstatus = deleteProposer.get();
		if(updatedeletedstatus.getStatus() == 'N')
		{
			throw new Exception("Proposer is deleted");
			
		}
		
		updatedeletedstatus.setStatus('N');
		return proposalRepo.save(updatedeletedstatus);
		
		
	}

	@Override
	public List<Proposer> getproposer() 
	{
		return proposalRepo.findAll();
	}

	
	 @Override public Proposer update(Long id, Proposer proposer) {
	  Optional<Proposer> getpropose = proposalRepo.findById(id);
	  
	  Proposer oldproposer = getpropose.get();
	  oldproposer.setAadharnumber(proposer.getAadharnumber());
	  oldproposer.setAddress(proposer.getAddress());
	 oldproposer.setAnnualincome(proposer.getAnnualincome());
	  oldproposer.setCity(proposer.getCity());
	  oldproposer.setDateofbirth(proposer.getDateofbirth());
	  oldproposer.setEmail(proposer.getEmail());
	  oldproposer.setGender(proposer.getGender()); //
	  oldproposer.setId(proposer.getId());
	  oldproposer.setMaritalstatus(proposer.getMaritalstatus());
	  oldproposer.setName(proposer.getName());
	  oldproposer.setPannumber(proposer.getPannumber());
	  oldproposer.setPhonenumber(proposer.getPhonenumber());
	  oldproposer.setPincode(proposer.getPincode());
	 oldproposer.setState(proposer.getState());
	  
	 
	  Proposer updatedProposer = proposalRepo.save(oldproposer); return
	  updatedProposer;
	  
	  }

	@Override
	public Proposer saveproposerdto(ProposerDTO proposerDTO) {
		Proposer proposer = new Proposer();

			if (proposerDTO.getAadharnumber() == null || proposerDTO.getAadharnumber().length() != 12 || proposalRepo.existsByAadharnumber(proposerDTO.getAadharnumber()))
			{
				throw new IllegalArgumentException("Aadhaar number must be exactly 12 digits.");
			}
			proposer.setAadharnumber(proposerDTO.getAadharnumber());

			if (proposerDTO.getAddress() == null || proposerDTO.getAddress().isEmpty()) {
				throw new IllegalArgumentException("Address cannot be null or empty.");
			}
			proposer.setAddress(proposerDTO.getAddress());

			if (proposerDTO.getAnnualincome() == null || proposerDTO.getAnnualincome().isEmpty()) {
				throw new IllegalArgumentException("Annual income must be a positive value.");
			}
			proposer.setAnnualincome(proposerDTO.getAnnualincome());

			if (proposerDTO.getCity() == null || proposerDTO.getCity().isEmpty()) {
				throw new IllegalArgumentException("City cannot be null or empty.");
			}
			proposer.setCity(proposerDTO.getCity());

			if (proposerDTO.getDateofbirth() == null) {
				throw new IllegalArgumentException("Date of birth cannot be null.");
			}
			proposer.setDateofbirth(proposerDTO.getDateofbirth());

			if (proposerDTO.getEmail() == null || !proposerDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
				throw new IllegalArgumentException("Invalid email format.");
			}
			proposer.setEmail(proposerDTO.getEmail());


			if (proposerDTO.getGender() == null || proposerDTO.getGender().isEmpty()) {
				throw new IllegalArgumentException("Gender cannot be null or empty.");
			}
			proposer.setGender(proposerDTO.getGender());

			if (proposerDTO.getMaritalstatus() == null || proposerDTO.getMaritalstatus().isEmpty()) {
				throw new IllegalArgumentException("Marital status cannot be null or empty.");
			}
			proposer.setMaritalstatus(proposerDTO.getMaritalstatus());

			if (proposerDTO.getName() == null || proposerDTO.getName().isEmpty()) {
				throw new IllegalArgumentException("Name cannot be null or empty.");
			}
			proposer.setName(proposerDTO.getName());

			if (proposerDTO.getPannumber() == null || proposerDTO.getPannumber().length() != 10  || !proposerDTO.getPannumber().matches("^[A-Z]{5}[0-9]{4}[A-Z]{1}$"))
			{
				throw new IllegalArgumentException("PAN number must be exactly 10 characters.");
			}
			proposer.setPannumber(proposerDTO.getPannumber());

			if (proposerDTO.getPhonenumber() == null || proposerDTO.getPhonenumber().length() != 10) {
				throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
			}
			proposer.setPhonenumber(proposerDTO.getPhonenumber());

			if(proposerDTO.getPincode() == null || proposerDTO.getPincode().length() != 6)
			{
				throw new IllegalArgumentException("Pincode must of 6 digit");
			}
			proposer.setPincode(proposerDTO.getPincode());

			if (proposerDTO.getState() == null || proposerDTO.getState().isEmpty()) {
				throw new IllegalArgumentException("State cannot be null or empty.");
			}
			proposer.setState(proposerDTO.getState());

			proposer.setStatus('Y');

			return proposalRepo.save(proposer);

	}


	@Override
	public Proposer updatedto(Long id, ProposerDTO proposerDTO) {
		
		Proposer proposer = new Proposer();
		proposer.setAadharnumber(proposerDTO.getAadharnumber());
		proposer.setAddress(proposerDTO.getAddress());
		proposer.setAnnualincome(proposerDTO.getAnnualincome());
		proposer.setCity(proposerDTO.getCity());
		proposer.setDateofbirth(proposerDTO.getDateofbirth());
		proposer.setEmail(proposerDTO.getEmail());
		proposer.setGender(proposerDTO.getGender());
		proposer.setMaritalstatus(proposerDTO.getMaritalstatus());
		proposer.setName(proposerDTO.getName());
		proposer.setPannumber(proposerDTO.getPannumber());
		proposer.setPhonenumber(proposerDTO.getPhonenumber());
		proposer.setPincode(proposerDTO.getPincode());
		proposer.setState(proposerDTO.getState());
		
		return proposalRepo.save(proposer);
	}



}
