package com.example.proposal.controller;


import com.example.proposal.dto.ProposerDTO;
import com.example.proposal.repository.ProposalRepo;
import com.example.proposal.pagenation.ProposerPage;
import com.example.proposal.responsehandler.ResponseHandler;
import com.example.proposal.service.ProposalService;
import com.example.proposal.ServiceImpl.ProposalServiceImpl;
import com.example.proposal.entity.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("proposer/")
public class ProposerController {


    @Autowired
    private ProposalServiceImpl proposalService;

    @Autowired
    private ProposalRepo proposalRepo;


    @PostMapping("proposer_register")
    public ResponseHandler dto_register(@RequestBody ProposerDTO proposerDTO) {
        ResponseHandler responseHandler = new ResponseHandler<>();
        try {
            responseHandler.setTotalRecord(proposalRepo.count());
            Proposer registerDto = proposalService.saveProposerDto(proposerDTO);
            responseHandler.setData(registerDto);
            responseHandler.setStatus(true);
            responseHandler.setMessage("Proposer registered successfully");
        }
       /* catch (IllegalArgumentException e)
        {
            Proposer registerdto = proposalService.saveproposerdto(proposerDTO);
            responseHandler.setData(registerdto);
            responseHandler.setStatus(false);
            responseHandler.setMessage("Info");
        }*/ catch (Exception e) {
            //Proposer registerdto = proposalService.saveproposerdto(proposerDTO);
            responseHandler.setTotalRecord(proposalRepo.count());
            responseHandler.setData(new ArrayList<>());
            responseHandler.setStatus(false);
            responseHandler.setMessage(e.getMessage());
        }
        return responseHandler;
    }

    @GetMapping("get_proposer")
    public ResponseHandler<List<Proposer>> get_proposers() {
        ResponseHandler<List<Proposer>> responseHandler = new ResponseHandler<>();
        try {
            List<Proposer> getProposer = proposalRepo.findByStatus('Y');
            responseHandler.setTotalRecord(getProposer.size());
            responseHandler.setMessage("Success");
            responseHandler.setData(getProposer);
            responseHandler.setStatus(true);
        } catch (IllegalArgumentException e) {
//            Proposer registerDto = proposalService.saveproposerdto();
            responseHandler.setData(new ArrayList<>());
            responseHandler.setStatus(false);
            responseHandler.setMessage("Info");
        }
        return responseHandler;
    }


    @DeleteMapping("delete_proposer/{id}")
    public ResponseHandler<?> delete_by_id(@PathVariable Long id) throws Exception {
        ResponseHandler responseHandler = new ResponseHandler<>();
        try {
            List<Proposer> getProposer = proposalService.getProposer();
            proposalService.delete(id);
            responseHandler.setStatus(true);
            responseHandler.setMessage("Deleted");
            responseHandler.setData(getProposer);
        } catch (IllegalArgumentException e) {
            responseHandler.setStatus(false);
            responseHandler.setMessage("Info");
        }

        return responseHandler;


    }


    @PutMapping("update_proposer/{id}")
    public ResponseHandler<List<ProposalService>> new_proposer(@PathVariable Long id, @RequestBody ProposerDTO updatedProposerdto) {
        ResponseHandler responseHandler = new ResponseHandler();
        try {
            responseHandler.setStatus(true);
            responseHandler.setData(proposalService);
            responseHandler.setMessage("Successfull");
        } catch (IllegalArgumentException e) {
            responseHandler.setMessage("Unsuccessfull");
            responseHandler.setData(new ArrayList<>());
            responseHandler.setStatus(false);
        }
        return responseHandler;
    }

    @PostMapping("pagination_filtering")
    public ResponseHandler<List<Proposer>> getAllByPaging(@RequestBody ProposerPage proposerPage) {
        ResponseHandler<List<Proposer>> responseHandler = new ResponseHandler<>();
        try {
            List<Proposer> proposers = proposalService.getDetails(proposerPage);
            if (proposers == null || proposers.isEmpty()) {
                responseHandler.setTotalRecord(proposalService.getTotalRecord());
                responseHandler.setStatus(false);
                responseHandler.setData(Collections.emptyList());
                responseHandler.setMessage("No proposers found.");
            } else {
                responseHandler.setTotalRecord(proposalService.getTotalRecord());
                responseHandler.setStatus(true);
                responseHandler.setData(proposers);
                responseHandler.setMessage("Proposers retrieved successfully.");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
//            responseHandler.setTotalRecord(proposers.size());
            responseHandler.setStatus(false);
            responseHandler.setData(Collections.emptyList());
            responseHandler.setMessage(e.getMessage());

        }
        /*try {
            List<Proposer> proposeres = proposalService.getfiltering(proposerPage);
            if (proposeres == null || proposeres.isEmpty()) {
                responseHandler.setStatus(false);
                responseHandler.setData(Collections.emptyList());
                responseHandler.setMessage("No proposers found.");
            } else {
                responseHandler.setStatus(true);
                responseHandler.setData(proposeres);
                responseHandler.setMessage("Proposers retrieved successfully.");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            responseHandler.setStatus(false);
            responseHandler.setData(Collections.emptyList());
            responseHandler.setMessage(e.getMessage());

        }*/
        return responseHandler;
    }

   /* @PostMapping("/getbyfilter")
    public ResponseHandler<List<Proposer>> getAllByFiltering(@RequestBody ProposerPage proposerPage) {
        ResponseHandler<List<Proposer>> responseHandler = new ResponseHandler<>();
        try {
            List<Proposer> proposers = proposalService.getfiltering(proposerPage);
            if (proposers == null || proposers.isEmpty()) {
                responseHandler.setStatus(false);
                responseHandler.setData(Collections.emptyList());
                responseHandler.setMessage("No proposers found.");
            } else {
                responseHandler.setStatus(true);
                responseHandler.setData(proposers);
                responseHandler.setMessage("Proposers retrieved successfully.");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            responseHandler.setStatus(false);
            responseHandler.setData(Collections.emptyList());
            responseHandler.setMessage(e.getMessage());

        }
        return responseHandler;
    }*/


    @GetMapping("excel")
    public void generateExcelReport(HttpServletResponse response) throws Exception {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=Proposer_Details.xls";

        response.setHeader(headerKey, headerValue);

        proposalService.generateExcel(response);

        response.flushBuffer();
    }

    @GetMapping("/generate_sample")
    public ResponseHandler generateExcelSample(HttpServletResponse response) {
        ResponseHandler responseHandler = new ResponseHandler();

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String currentDateTime = dateFormat.format(new Date());
            String uuid = UUID.randomUUID().toString();


            String fileName = "Proposer_Details_" + uuid + "_" + currentDateTime + ".xlsx";
            String filePath = "D:\\" + fileName;


            proposalService.sampleExcel(filePath);

            responseHandler.setStatus(true);
            responseHandler.setMessage("Excel file generated successfully");
            responseHandler.setData(filePath);
        } catch (Exception e) {
            responseHandler.setStatus(false);
            responseHandler.setMessage("Failed to generate Excel: " + e.getMessage());
            responseHandler.setData(Collections.emptyList());
        }

        return responseHandler;
    }

//    ProposalServiceImpl proposalServices = new ProposalServiceImpl();

    /*  public Map<String,Integer> getSuccessMap()
      {
          return proposalService.
      }*/
    @PostMapping(value = "/import_Personal_Data", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseHandler importPersonalDetails(
            @RequestParam("file") MultipartFile file) {

        ResponseHandler response = new ResponseHandler();

        try {

            List<Proposer> proposers = proposalService.importPersonalDetailsFromExcel(file);
            response.setStatus(true);
            response.setData(proposers);
            response.setTotalRecord(proposalService.totalRecords());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(false);
            response.setMessage("Failed to import Excel file.");
            response.setData(new ArrayList<>());
        }

        return response;
    }
    @PostMapping(value = "/upload_excel_scheduler", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseHandler<String> uploadExcelMandatoryUsingScheduler(@RequestParam("file") MultipartFile file) {
        ResponseHandler<String> responseHandler = new ResponseHandler<>();
        try {
            Map<String, Object> resultMap = proposalService.saveProposersFromExcelMandatoryUsingScheduler(file);
            if (resultMap.containsKey("queueId")) {
                responseHandler.setStatus(true);
                responseHandler.setData(resultMap);
                responseHandler.setMessage((String) resultMap.get("message"));
                responseHandler.setTotalRecord((Integer) resultMap.get("rowCount"));
                return responseHandler;
            }
            Integer total = (Integer) resultMap.get("totalCount");
            Integer success = (Integer) resultMap.get("successCount");
            Integer failed = (Integer) resultMap.get("failedCount");

            List<Proposer> savedProposers = (List<Proposer>) resultMap.get("addedProposers");

            responseHandler.setStatus(true);
            responseHandler.setData(savedProposers);
            responseHandler.setMessage(
                    "Upload completed | total: " + total + " | success: " + success + " | failed: " + failed);
            responseHandler.setTotalRecord(total);

        } catch (Exception e) {
            e.printStackTrace();
            responseHandler.setStatus(false);
            responseHandler.setData(new ArrayList<>());
            responseHandler.setMessage("Failed to process Excel file.");
        }

        return responseHandler;
    }


    // *********************************************** Enum *************************************************************

    @GetMapping("get_gender")
    public List<Gender> getallgender() {
        return Arrays.asList(Gender.values());
    }

    @GetMapping("get_marital_status")
    public List<MaritalStatus> getmarital() {
        return Arrays.asList(MaritalStatus.values());
    }

    @GetMapping("get_nationality")
    public List<Nationality> getnationality() {
        return Arrays.asList(Nationality.values());
    }

    @GetMapping("get_occupation")
    public List<Occupation> getoccupation() {
        return Arrays.asList(Occupation.values());
    }

    @GetMapping("get_title")
    public List<Title> gettitle() {
        return Arrays.asList(Title.values());
    }


    // *********************************** Pagination ***********************************************************************


/*    @GetMapping("get_page/{name}")
    public Page<Proposer> getbyname(@PathVariable String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
    {
        return proposalRepo.findByName(name, PageRequest.of(page, size));
    }*/

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























/*@Autowired
ProposalServiceImpl proposalServiceimpl;

@GetMapping("/items")
public List<AbstractReadWriteAccess.Item> listItems(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "size", defaultValue = "5") int size) {
    return proposalServiceimpl.getItems(page, size);
}

@GetMapping("/items/total")
public long getTotalItems() {
    return proposalServiceimpl.getTotalItems();
}*/
