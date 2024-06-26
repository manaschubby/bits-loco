package com.oops_project.bits_loco.Message;

import com.oops_project.bits_loco.Utils.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/message")
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
    //
    // @GetMapping(path = "/inbox")
    // public ResponseEntity<Object> getInbox(@RequestParam int userId) {
    //     List<MessageModel> messages = messageService.getInbox(userId);
    //     return ResponseEntity.ok(messages);
    // }
    //
    // @GetMapping(path = "/outbox")
    // public ResponseEntity<Object> getOutbox(@RequestParam int userId) {
    //     List<MessageModel> messages = messageService.getOutbox(userId);
    //     return ResponseEntity.ok(messages);
    // }
    //
    // @GetMapping(path = "/trip")
    // public ResponseEntity<Object> getTripMessages(@RequestParam int tripId) {
    //     List<MessageModel> messages = messageService.getTripMessages(tripId);
    //     return ResponseEntity.ok(messages);
    // }
    //
    // @GetMapping(path = "/unread")
    // public ResponseEntity<Object> getUnreadMessages(@RequestParam int userId) {
    //     List<MessageModel> messages = messageService.getUnreadMessages(userId);
    //     return ResponseEntity.ok(messages);
    // }
    //
    // @PostMapping(path = "/read")
    // public
}
