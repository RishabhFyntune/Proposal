package com.example.proposal.ServiceImpl;


import com.example.proposal.entity.User;
import com.example.proposal.repository.UserRepo;
import com.example.proposal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {


    @Autowired
    UserRepo userRepo;


    @Override
    public String registerUser(User user)
    {
        if(user.getUserName() == null || user.getUserName().isEmpty() ||
                user.getUserPassword() == null || user.getUserPassword().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getRole() == null || user.getRole().isEmpty() )
        {
            return "All fieids are manadatory";
        }

}
