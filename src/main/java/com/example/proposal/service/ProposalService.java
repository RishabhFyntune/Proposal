package com.example.proposal.service;

import java.util.HashMap;
import java.util.List;

import com.example.proposal.pagenation.ProposerPage;

import com.example.proposal.dto.ProposerDto;
import com.example.proposal.entity.Proposer;
import com.example.proposal.response.ProposerResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;


public interface ProposalService
{
	public Proposer register(Proposer proposer);
	public Proposer delete(Long id) throws Exception;
	public List<Proposer> getProposer();
	public ProposerResponse update(Long id, Proposer proposer);
	public ProposerResponse getUserById(Integer userId);
	Proposer getProposerById(Long id);

	Proposer saveProposer(Proposer proposer);

	public Proposer updateDto(Long id, ProposerDto proposerDTO);
	public Proposer saveProposerDto(ProposerDto proposerDTO);
	List<Proposer> getDetails(ProposerPage proposerPage);

	public Integer getTotalRecord();
	public void generateExcel(HttpServletResponse httpServletResponse) throws Exception;
	public String sampleExcel(String httpServletResponse) throws Exception;

	public Integer totalRecords();


	public HashMap<String,Object> saveProposersFromExcelMandatoryUsingScheduler(MultipartFile multipartFile) throws Exception;
	public void queueScheduler();

}
