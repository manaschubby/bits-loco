package com.oops_project.bits_loco.Message;

import com.oops_project.bits_loco.Utils.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/message")
public class MessageController {
    MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

     @PostMapping(path = "/send")
     public ResponseEntity<Object> sendMessage(@RequestBody Map<String, String> requestBody) {
         // Verify Existence of all fields
         if (!requestBody.containsKey("senderId") || !requestBody.containsKey("receiverId") || !requestBody.containsKey("tripId") || !requestBody.containsKey("message")) {
             return ErrorResponse.from("error", "All fields are required");
         }

         try {
             messageService.sendMessage(requestBody);
             return ResponseEntity.ok(Map.of("message", "Message Sent"));
         }
         catch (IllegalArgumentException e) {
             return ErrorResponse.from("error", e.getMessage(), "message", "Message not sent");
         }

     }

     @GetMapping(path = "")
     public ResponseEntity<Object> getMessages(@RequestParam Optional<String> userId) {
        // Verify Existence of all fields
         if (userId.isEmpty()) {
             return ErrorResponse.from("error", "User ID is required");
         }
         try {
             return messageService.getMessages(userId.get());
         }
         catch (IllegalArgumentException e) {
             return ErrorResponse.from("error", e.getMessage(), "message", "Messages not fetched");
         }
     }
}
