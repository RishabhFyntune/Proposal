package com.example.proposal.service;

import com.example.proposal.entity.Users;

public interface UserService
{
    public String registerUser(Users users);

    public String loginUser(String username, String password);
}
