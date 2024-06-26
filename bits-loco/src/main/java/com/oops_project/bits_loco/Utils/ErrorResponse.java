package com.oops_project.bits_loco.Utils;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ErrorResponse {
    public static ResponseEntity<String> from(String message) {
        return ResponseEntity.badRequest().body(message);
    }

    public static ResponseEntity<Object> from(Exception e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }

    public static ResponseEntity<Object> from(Object o) {
        return ResponseEntity.badRequest().body(o);
    }

    public static ResponseEntity<Object> from(String ...args) {
        Map<String, String> response = new java.util.HashMap<>();
        for (int i = 0; i < args.length; i+=2) {
            response.put(args[i], args[i+1]);
        }
        return ResponseEntity.badRequest().body(response);
    }
}
