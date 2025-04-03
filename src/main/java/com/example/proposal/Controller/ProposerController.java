package com.example.proposal.Controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import com.example.proposal.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.proposal.DTO.ProposerDTO;
import com.example.proposal.Repository.ProposalRepo;
import com.example.proposal.Service.ProposalService;

@RestController
//@RequestMapping("/proposer")
public class ProposerController
{



	@Autowired
	private ProposalService proposalService;



	
	  @PostMapping("/register") public ResponseEntity<Proposer>
	  register(@RequestBody Proposer proposerEntity)
	  
	  { Proposer proposer = proposalService.register(proposerEntity); 
	  return new ResponseEntity<>(proposer,HttpStatus.OK);
	  
	 }
	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletebyid(@PathVariable Long id) throws Exception
	{
		proposalService.delete(id);
		return ResponseEntity.ok("Proposer by id" + id + "deleted successfull"); 

	}

	@GetMapping("/getall")
	public ResponseEntity<List<Proposer>> getproposer()
	{
		List<Proposer> allProposers = proposalService.getproposer();
		return ResponseEntity.ok(allProposers);

	}

	
	/*
	 * @PutMapping("/update/{id}") public
	 * ResponseEntity<Proposer>newProposer(@PathVariable Long id, @RequestBody
	 * Proposer proposer) { Proposer updatedProposer = proposalService.update(id,
	 * proposer); return new
	 * ResponseEntity<Proposer>(updatedProposer,HttpStatus.OK);
	 * 
	 * }
	 */
	

	@PostMapping("/dtoregister")
	public ResponseEntity<Proposer> dtoregister(@RequestBody ProposerDTO proposerDTO )
	{
	   Proposer registerdto = proposalService.saveproposerdto(proposerDTO);
		return new ResponseEntity<Proposer>(registerdto,HttpStatus.OK);

	}


	@PutMapping("/update/{id}") 
	public Proposer newProposer(@PathVariable Long id, @RequestBody ProposerDTO  updatedProposerdto) { 
		return proposalService.updatedto(id, updatedProposerdto);
	 }

	 @GetMapping("/getgender")
	public List<Gender> getallgender()
	 {
		 return Arrays.asList(Gender.values());
	 }

	@GetMapping("/getmaritalstatus")
	public List<MaritalStatus> getmarital()
	{
		return Arrays.asList(MaritalStatus.values());
	}
	@GetMapping("/getnationality")
	public List<Nationality> getnationality()
	{
		return Arrays.asList(Nationality.values());
	}

	@GetMapping("/getoccupation")
	public List<Occupation> getoccupation()
	{
		return Arrays.asList(Occupation.values());
	}

	@GetMapping("/gettitle")
	public List<Title> gettitle()
	{
		return Arrays.asList(Title.values());
	}

}
