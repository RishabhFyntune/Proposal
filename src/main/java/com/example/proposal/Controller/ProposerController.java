package com.example.proposal.Controller;


import com.example.proposal.DTO.ProposerDTO;
import com.example.proposal.Repository.ProposalRepo;
import com.example.proposal.ResponseHandler.ResponseHandler;
import com.example.proposal.Service.ProposalService;
import com.example.proposal.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
//@RequestMapping("/proposer")
public class ProposerController {


    @Autowired
    private ProposalService proposalService;

    @Autowired
    private ProposalRepo proposalRepo;


    @PostMapping("/dtoregister")
    public ResponseHandler dtoregister(@RequestBody ProposerDTO proposerDTO) {
        ResponseHandler responseHandler = new ResponseHandler<>();
        try {
            Proposer registerdto = proposalService.saveproposerdto(proposerDTO);
            responseHandler.setData(registerdto);
            responseHandler.setStatus(true);
            responseHandler.setMessage("Proposer registered successfully");
        }
       /* catch (IllegalArgumentException e)
        {
            Proposer registerdto = proposalService.saveproposerdto(proposerDTO);
            responseHandler.setData(registerdto);
            responseHandler.setStatus(false);
            responseHandler.setMessage("Info");
        }*/
        catch (Exception e)
        {
            //Proposer registerdto = proposalService.saveproposerdto(proposerDTO);
            responseHandler.setData(new ArrayList<>());
            responseHandler.setStatus(false);
            responseHandler.setMessage(e.getMessage());
        }
        return responseHandler;
    }

    @GetMapping("/get")
    public ResponseHandler<List<Proposer>> getproposers(ProposerDTO proposerDTO)
    {
        ResponseHandler<List<Proposer>> responseHandler = new ResponseHandler<>();
        try
        {
            List<Proposer> getproposer = proposalRepo.findByStatus('Y');
            responseHandler.setMessage("Success");
            responseHandler.setData(getproposer);
            responseHandler.setStatus(true);
        } catch (IllegalArgumentException e) {
            Proposer registerdto = proposalService.saveproposerdto(proposerDTO);
            responseHandler.setData(registerdto);
            responseHandler.setStatus(false);
            responseHandler.setMessage("Info");
        }
        return responseHandler;
    }



    @DeleteMapping("/delete/{id}")
    public ResponseHandler<?> deletebyid(@PathVariable Long id) throws Exception {
        ResponseHandler responseHandler = new ResponseHandler<>();
        try {
            List<Proposer> getproposer = proposalService.getproposer();
            proposalService.delete(id);
            responseHandler.setStatus(true);
            responseHandler.setMessage("Deleted");
            responseHandler.setData(getproposer);
        }
        catch (IllegalArgumentException e)
        {
            responseHandler.setStatus(false);
            responseHandler.setMessage("Info");
        }

        return responseHandler;


    }



    @PutMapping("/update/{id}")
    public Proposer newProposer(@PathVariable Long id, @RequestBody ProposerDTO updatedProposerdto) {
        return proposalService.updatedto(id, updatedProposerdto);
    }


    // *********************************************** Enum *************************************************************

    @GetMapping("/getgender")
    public List<Gender> getallgender() {
        return Arrays.asList(Gender.values());
    }

    @GetMapping("/getmaritalstatus")
    public List<MaritalStatus> getmarital() {
        return Arrays.asList(MaritalStatus.values());
    }

    @GetMapping("/getnationality")
    public List<Nationality> getnationality() {
        return Arrays.asList(Nationality.values());
    }

    @GetMapping("/getoccupation")
    public List<Occupation> getoccupation() {
        return Arrays.asList(Occupation.values());
    }

    @GetMapping("/gettitle")
    public List<Title> gettitle() {
        return Arrays.asList(Title.values());
    }


    // *********************************** Pagination ***********************************************************************


    @GetMapping("/getpage/{name}")
    public Page<Proposer> getbyname(@PathVariable String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return proposalRepo.findByName(name, PageRequest.of(page, size));
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


/*	@PostMapping("/dtoregister")
	public ResponseEntity<Proposer> dtoregister(@RequestBody ProposerDTO proposerDTO )
	{
	   Proposer registerdto = proposalService.saveproposerdto(proposerDTO);
		return new ResponseEntity<Proposer>(registerdto,HttpStatus.OK);

	}*/

     /*   @PostMapping("/register")
    public ResponseEntity<Proposer> register(@RequestBody Proposer proposerEntity) {
        Proposer proposer = proposalService.register(proposerEntity);
        return new ResponseEntity<>(proposer, HttpStatus.OK);

    }*/
}
