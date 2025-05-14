package com.example.proposal.repository;

import com.example.proposal.entity.QueueTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueueRepo extends JpaRepository<QueueTable,Long>
{

    List<QueueTable> findByIsProcessed(Character n);
}
