package com.oops_project.bits_loco.User;

import com.oops_project.bits_loco.Utils.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "")
    public ResponseEntity<Object> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping(path = "")
    public ResponseEntity<Object> addUser(@RequestBody Map<String, String> user){
        // Verify Existence of all fields
        if (!user.containsKey("name")){
            return ErrorResponse.from("error", "Name is required");
        }
        if (!user.containsKey("email")){
            return ErrorResponse.from("error", "Email is required");
        }
        if (!user.containsKey("profilePicture")){
            return ErrorResponse.from("error", "Profile Picture is required");
        }
        if (!user.containsKey("phoneNumber")){
            return ErrorResponse.from("error", "Phone Number is required");
        }
        if (!user.containsKey("userType")){
            return ErrorResponse.from("error", "User Type is required");
        }
        if (!user.containsKey("gender")){
            return ErrorResponse.from("error", "Gender is required");
        }
        try {
            return ResponseEntity.ok(userService.createUser(user));
        } catch (IllegalArgumentException e) {
            return ErrorResponse.from(e);
        }
    }

    @PutMapping(path = "")
    public ResponseEntity<Object> updateUser(@RequestBody Map<String, String> user){
        // Verify Existence of all fields
        if (!user.containsKey("id")){
            return ErrorResponse.from("error", "ID is required");
        }
        try {
            return ResponseEntity.ok(userService.updateUser(user));
        } catch (IllegalArgumentException e) {
            return ErrorResponse.from(e);
        }
    }
}
