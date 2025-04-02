package com.example.proposal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proposal.Repository.ProposalRepo;
import com.example.proposal.model.Proposer;

@Service
public class ProposalServiceImpl implements ProposalService
{

	@Autowired
	private ProposalRepo proposalRepo;
	
	@Override
	public Proposer register(Proposer proposer) 
	{
		return proposalRepo.save(proposer);
	}

	@Override
	public void delete(Long id) 
	{
		proposalRepo.deleteById(id);
	}

	@Override
	public List<Proposer> getproposer() 
	{
		return proposalRepo.findAll();
	}

	@Override
	public Proposer update(Long id, Proposer proposer) 
	{
		Optional<Proposer> getpropose = proposalRepo.findById(id);
		
		Proposer oldproposer = getpropose.get();
		oldproposer.setAadharnumber(proposer.getAadharnumber());
		oldproposer.setAddress(proposer.getAddress());
		oldproposer.setAnnualincome(proposer.getAnnualincome());
		oldproposer.setCity(proposer.getCity());
		oldproposer.setDateofbirth(proposer.getDateofbirth());
		oldproposer.setEmail(proposer.getEmail());
		oldproposer.setGender(proposer.getGender());
//		oldproposer.setId(proposer.getId());
		oldproposer.setMaritalstatus(proposer.getMaritalstatus());
		oldproposer.setName(proposer.getName());
		oldproposer.setPannumber(proposer.getPannumber());
		oldproposer.setPhonenumber(proposer.getPhonenumber());
		oldproposer.setPincode(proposer.getPincode());
		oldproposer.setState(proposer.getState());
		
		
		Proposer updatedProposer =  proposalRepo.save(oldproposer);
		return updatedProposer;
		
	}
	
	

}
