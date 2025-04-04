package com.example.proposal.Service;

import java.util.List;
import java.util.Optional;

import com.example.proposal.model.Gender;
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
	  oldproposer.setDateOfBirth(proposer.getDateOfBirth());
	  oldproposer.setEmail(proposer.getEmail());
	  oldproposer.setGender(proposer.getGender());
	  oldproposer.setId(proposer.getId());
	  oldproposer.setMaritalstatus(proposer.getMaritalstatus());
	  oldproposer.setName(proposer.getName());
	  oldproposer.setPanNumber(proposer.getPanNumber());
	  oldproposer.setPhonenumber(proposer.getPhonenumber());
	  oldproposer.setPincode(proposer.getPincode());
	 oldproposer.setState(proposer.getState());
	  
	 
	  Proposer updatedProposer = proposalRepo.save(oldproposer); return
	  updatedProposer;
	  
	  }

	@Override
	public Proposer saveproposerdto(ProposerDTO proposerDTO) {
		Proposer proposer = new Proposer();

			if (proposerDTO.getAadharnumber() == null || proposerDTO.getAadharnumber().length() != 12)
			{
				throw new IllegalArgumentException("Aadhaar number must be exactly 12 digits.");
			}
			if(proposalRepo.existsByAadharnumber(proposerDTO.getAadharnumber()))
			{
				throw new IllegalArgumentException("Aadhaar number already present");
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

			if (proposerDTO.getDateOfBirth() == null) {
				throw new IllegalArgumentException("Date of birth cannot be null.");
			}
			proposer.setDateOfBirth(proposerDTO.getDateOfBirth());

			if (proposerDTO.getEmail() == null)
			{
				if (!proposerDTO.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$") || proposalRepo.existsByEmail(proposerDTO.getEmail()))
				{
					throw new IllegalArgumentException("Invalid email format.");
				}

			}
			proposer.setEmail(proposerDTO.getEmail());

			if (null == proposerDTO.getGender())
			{
				throw new IllegalArgumentException("Gender cannot be null or empty.");
			}
		if (!proposerDTO.getGender().equalsIgnoreCase("male") && !proposerDTO.getGender().equalsIgnoreCase("female")) {
			throw new IllegalArgumentException("Invalid gender. Only Male or Female are allowed.");
		}


		proposer.setGender(String.valueOf(proposerDTO.getGender()));



			if (proposerDTO.getMaritalstatus() == null)
			{
				throw new IllegalArgumentException("Marital status cannot be null or empty.");
			}
			if(!proposerDTO.getMaritalstatus().equalsIgnoreCase("single") && !proposerDTO.getMaritalstatus().equalsIgnoreCase("married") && !proposerDTO.getMaritalstatus().equalsIgnoreCase("divorce") )
			{
				throw new IllegalArgumentException("Enter valid marital status");
			}
			proposer.setMaritalstatus(proposerDTO.getMaritalstatus());


			if (proposerDTO.getName() == null || proposerDTO.getName().isEmpty()) {
				throw new IllegalArgumentException("Name cannot be null or empty.");
			}
			proposer.setName(proposerDTO.getName());
		boolean b = proposalRepo.existsByPanNumber(proposerDTO.getPanNumber());
		if(b)
		{
			throw new IllegalArgumentException("Pan card already present");
		}
		if (proposerDTO.getPanNumber() == null || proposerDTO.getPanNumber().isEmpty()) {
			if (!proposerDTO.getPanNumber().matches("^[A-Z]{5}[0-9]{4}[A-Z]{1}$")) {
				throw new IllegalArgumentException("PAN number must be exactly 10 characters.");
			}
		}

			proposer.setPanNumber(proposerDTO.getPanNumber());

			if (proposerDTO.getPhoneNumber() == null)
			{
				if(proposerDTO.getPhoneNumber().length() != 10 || proposalRepo.existsByPhonenumber(proposerDTO.getPhoneNumber()))
				{
					throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
				}
			}
			proposer.setPhonenumber(proposerDTO.getPhoneNumber());

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
		proposer.setDateOfBirth(proposerDTO.getDateOfBirth());
		proposer.setEmail(proposerDTO.getEmail());
		proposer.setGender(proposerDTO.getGender());
		proposer.setMaritalstatus(proposerDTO.getMaritalstatus());
		proposer.setName(proposerDTO.getName());
		proposer.setPanNumber(proposerDTO.getPanNumber());
		proposer.setPhonenumber(proposerDTO.getPhoneNumber());
		proposer.setPincode(proposerDTO.getPincode());
		proposer.setState(proposerDTO.getState());
		
		return proposalRepo.save(proposer);
	}



}
