package com.example.proposal.repository;

import com.example.proposal.responsehandler.ResponseExcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface responseExcelRepo extends JpaRepository<ResponseExcel, Long>
{
        //public ResponseExcel message(String response);
}
