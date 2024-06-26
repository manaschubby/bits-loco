package com.oops_project.bits_loco.Message;

import com.oops_project.bits_loco.User.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageService {
    MessageRepository messageRepository;
    UserRepository userRepository;


    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public void sendMessage(Map<String, String> message) throws IllegalArgumentException {
        MessageModel messageModel = new MessageModel();
        messageModel.setMessage(message.get("message"));

        int senderId = Integer.parseInt(message.get("senderId"));
        int receiverId = Integer.parseInt(message.get("receiverId"));
        int tripId = Integer.parseInt(message.get("tripId"));

        if (userRepository.findById(senderId).isEmpty() || userRepository.findById(receiverId).isEmpty()) {
            throw new IllegalArgumentException("Invalid sender or receiver");
        }

        messageModel.setSenderId(senderId);
        messageModel.setReceiverId(receiverId);
        messageModel.setTripId(tripId);

        messageRepository.save(messageModel);
    }

    public ResponseEntity<Object> getMessages(String userId) throws IllegalArgumentException {
        int id = Integer.parseInt(userId);
        if (userRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Invalid User ID");
        }
        List<MessageModel> messages = messageRepository.findAllBySenderId(id);
        messages.addAll(messageRepository.findAllByReceiverId(id));
        Map<String, Object> response = Map.of("messages", messages,"message","Messages fetched successfully");
        return ResponseEntity.ok(response);
    }
}
