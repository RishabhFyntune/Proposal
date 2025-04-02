package com.example.proposal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proposal.model.Proposer;

public interface ProposalRepo extends JpaRepository<Proposer, Long> 
{

}
