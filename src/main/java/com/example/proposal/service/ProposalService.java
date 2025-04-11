package com.example.proposal.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.proposal.pagenation.ProposerPage;

import com.example.proposal.dto.ProposerDTO;
import com.example.proposal.model.Proposer;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;


public interface ProposalService
{
	public Proposer register(Proposer proposer);
	public Proposer delete(Long id) throws Exception;
	public List<Proposer> getProposer();
	public Proposer update( Long id, Proposer proposer);

	Proposer getProposerById(Long id);

	Proposer saveProposer(Proposer proposer);

	public Proposer updateDto(Long id, ProposerDTO proposerDTO);
	public Proposer saveProposerDto(ProposerDTO proposerDTO);
	List<Proposer> getDetails(ProposerPage proposerPage);

	public Integer getTotalRecord();
	//List<Proposer> getfiltering(ProposerPage proposerPage);
	public void generateExcel(HttpServletResponse httpServletResponse) throws Exception;
	public String sampleExcel(String httpServletResponse) throws Exception;
//	public void saveFileData(MultipartFile multipartFile) throws IOException;
}
