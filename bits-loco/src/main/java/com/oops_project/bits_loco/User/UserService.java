package com.oops_project.bits_loco.User;

import com.oops_project.bits_loco.Utils.Constants.Gender;
import com.oops_project.bits_loco.Utils.Constants.UserTypes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel createUser(Map<String, String> requestMap) throws IllegalArgumentException {
        UserModel user = new UserModel();
        user.setName(requestMap.get("name"));
        // Check if email is valid
        String email = requestMap.get("email");
        if (!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")){
            throw new IllegalArgumentException("Invalid email");
        }
        user.setEmail(email);
        user.setProfilePicture(requestMap.get("profilePicture"));
        user.setPhoneNumber(requestMap.get("phoneNumber"));
        try {
            user.setUserType(UserTypes.valueOf(requestMap.get("userType")));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid User Type");
        }
        try {
            user.setGender(Gender.valueOf(requestMap.get("gender")));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Gender");
        }

        return userRepository.save(user);
    }

    public UserModel updateUser(Map<String, String> requestMap) throws IllegalArgumentException {
        Optional<UserModel> optionalUser = userRepository.findById(Integer.parseInt(requestMap.get("id")));
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        UserModel user = optionalUser.get();
        if (requestMap.containsKey("name")) {
            user.setName(requestMap.get("name"));
        }
        if (requestMap.containsKey("profilePicture")) {
            user.setProfilePicture(requestMap.get("profilePicture"));
        }
        if (requestMap.containsKey("phoneNumber")) {
            user.setPhoneNumber(requestMap.get("phoneNumber"));
        }
        if (requestMap.containsKey("userType")) {
            try {
                user.setUserType(UserTypes.valueOf(requestMap.get("userType")));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid User Type");
            }
        }

        userRepository.save(user);
        return user;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }
}
