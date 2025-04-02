package com.example.proposal.Service;

import java.util.List;

//import com.example.proposal.model.Proposal;
import com.example.proposal.model.Proposer;


public interface ProposalService 
{
	public Proposer register(Proposer proposer);
	public Proposer delete(Long id) throws Exception;
	public List<Proposer> getproposer();
	public Proposer update( Long id, Proposer proposer);
		

}
