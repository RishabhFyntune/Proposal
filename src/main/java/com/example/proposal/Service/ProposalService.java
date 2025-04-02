package com.example.proposal.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proposal.DTO.ProposerDTO;
//import com.example.proposal.model.Proposal;
import com.example.proposal.model.Proposer;

@Service
public interface ProposalService 
{
	public Proposer register(Proposer proposer);
	public Proposer delete(Long id) throws Exception;
	public List<Proposer> getproposer();
	public Proposer update( Long id, Proposer proposer);
	public Proposer updatedto( Long id, ProposerDTO proposerDTO);
	
	public Proposer saveproposerdto(ProposerDTO proposerDTO);
		

}
