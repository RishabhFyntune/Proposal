package com.example.proposal.ServiceImpl;


import com.example.proposal.config.JwtUtil;
import com.example.proposal.entity.Users;
import com.example.proposal.repository.UserRepo;
import com.example.proposal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public UserServiceImpl(UserRepo userRepository, JwtUtil jwtUtil )
    {
        this.userRepo = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String registerUser(Users users) {

        if(users.getUserName() == null || users.getUserName().isEmpty() ||
                users.getUserPassword() == null || users.getUserPassword().isEmpty() ||
                users.getEmail() == null || users.getEmail().isEmpty() ||
                users.getRole() == null || users.getRole().isEmpty() ) {
            return "All fieids are manadatory";
        }


        if (userRepo.findByUserName(users.getUserName()).isPresent()) {
            return "Username already exists";
        }
        String hashedPassword =  passwordEncoder.encode(users.getUserPassword());
        users.setUserPassword(hashedPassword);
        userRepo.save(users);
        return "User registered successfully";
    }
    @Override
    public String loginUser(String username, String password) {
        Optional<Users> existingUser = userRepo.findByUserName(username);

        if (existingUser.isPresent()) {
            Users userlogin = existingUser.get();
            if (passwordEncoder.matches(password, userlogin.getUserPassword())) {
                return jwtUtil.generateToken(userlogin.getUserName(), userlogin,null);
            }
        }
        return "Invalid username or password";
    }

}
