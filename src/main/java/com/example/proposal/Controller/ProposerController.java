package com.example.proposal.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.proposal.Repository.ProposalRepo;
import com.example.proposal.Service.ProposalServiceImpl;
import com.example.proposal.model.Proposer;

import jakarta.websocket.server.PathParam;

@RestController
//@RequestMapping("/proposer")
public class ProposerController 
{

    private ProposalRepo proposalRepo;

	@Autowired
	private ProposalServiceImpl proposalServiceImpl;

    ProposerController(ProposalRepo proposalRepo) {
        this.proposalRepo = proposalRepo;
    }
	
	@PostMapping("/register")
	public ResponseEntity<Proposer> register(@RequestBody Proposer proposerEntity)
	
	{
		Proposer proposer = proposalServiceImpl.register(proposerEntity);
		return new ResponseEntity<>(proposer,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletebyid(@PathVariable Long id)
	{
		proposalServiceImpl.delete(id);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Proposer>> getproposer()
	{
		 List<Proposer> allProposers = proposalServiceImpl.getproposer();
		 return ResponseEntity.ok(allProposers);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Proposer> newProposer(@PathVariable Long id, @RequestBody Proposer proposer)
	{
		Proposer updatedProposer =  proposalServiceImpl.update(id, proposer);
		return new ResponseEntity<Proposer>(updatedProposer,HttpStatus.OK);
		
	}
	
	
}
