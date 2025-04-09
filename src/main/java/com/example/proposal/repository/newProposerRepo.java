package com.example.proposal.repository;

import com.example.proposal.model.GenderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface newProposerRepo extends JpaRepository<GenderType,Integer>
{
    Optional<GenderType> findByGenderType(String genderType);
}
