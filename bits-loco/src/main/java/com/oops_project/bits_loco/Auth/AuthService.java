package com.oops_project.bits_loco.Auth;

import com.oops_project.bits_loco.User.UserModel;
import com.oops_project.bits_loco.User.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel login(String email){
        return userRepository.findByEmail(email);
    }
}
