package com.oops_project.bits_loco.Message;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<MessageModel, Integer> {
    List<MessageModel> findAllBySenderId(int senderId);
    List<MessageModel> findAllByReceiverId(int receiverId);
    List<MessageModel> findAllByTripId(int tripId);
}

