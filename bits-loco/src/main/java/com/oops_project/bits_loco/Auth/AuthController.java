package com.oops_project.bits_loco.Auth;

import com.oops_project.bits_loco.User.UserModel;
import com.oops_project.bits_loco.Utils.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> requestBody) {
        // Verify Existence of all fields
        if (!requestBody.containsKey("email")) {
            return ErrorResponse.from("error", "Email is required");
        }

        UserModel user = authService.login(requestBody.get("email"));
        if (user == null) {
            return ErrorResponse.from("error", "User not found", "message", "Login Failed");
        }

        return ResponseEntity.ok(Map.of("message", "Login Successful", "id", user.getId()));
    }
}
