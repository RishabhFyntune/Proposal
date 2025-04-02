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
	public Proposer saveproposerdto(ProposerDTO proposerDTO) 
	{
		Proposer proposer = new Proposer();
		proposer.setStatus('Y');
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
