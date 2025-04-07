package com.example.proposal.Repository;


import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proposal.model.Proposer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProposalRepo extends JpaRepository<Proposer, Long> 
{
   Page<Proposer> findByName(String name, Pageable pageable);

   boolean existsByAadharnumber(String aadharnumber);
   boolean existsByPanNumber(String panNumber);
   List<Proposer> findByStatus(Character status);
   boolean existsByEmail(String email);
   boolean existsByPhonenumber(String phoneNumber);
}
