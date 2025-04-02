package com.example.proposal.Service;

import java.util.List;

//import com.example.proposal.model.Proposal;
import com.example.proposal.model.Proposer;


public interface ProposalService 
{
	public Proposer register(Proposer proposer);
	public void delete(Long id);
	public List<Proposer> getproposer();
	public Proposer update( Long id, Proposer proposer);
		

}
