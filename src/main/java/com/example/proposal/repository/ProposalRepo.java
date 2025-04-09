package com.example.proposal.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proposal.model.Proposer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepo extends JpaRepository<Proposer, Long> 
{
   Page<Proposer> findByName(String name, Pageable pageable);

   boolean existsByAadharnumber(String aadharnumber);
   boolean existsByPanNumber(String panNumber);
   List<Proposer> findByStatus(Character status);
   boolean existsByEmail(String email);
   boolean existsByPhoneNumber(String phoneNumber);
//   public Proposer proposerGenderId(int id);
}
