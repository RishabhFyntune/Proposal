package com.example.proposal.repository;

import com.example.proposal.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Integer>
{

    Boolean existsByUserName(String userName);

    Optional<Users> findByUserName(String userName);
}
