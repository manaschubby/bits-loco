package com.oops_project.bits_loco.Booking;

import com.oops_project.bits_loco.Utils.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/booking")
public class BookingController {
    BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

     @PostMapping(path = "/book")
     public ResponseEntity<Object> book(@RequestBody Map<String, String> requestBody) {
         // Verify Existence of all fields
         if (!requestBody.containsKey("tripId") || !requestBody.containsKey("riderId") || !requestBody.containsKey("seatNumber") || !requestBody.containsKey("pickUpPoint")) {
             return ErrorResponse.from("error", "All fields are required");
         }

         try {
             BookingModel bookingModel = new BookingModel();
             bookingModel.setTripId(Integer.parseInt(requestBody.get("tripId")));
             bookingModel.setRiderId(Integer.parseInt(requestBody.get("riderId")));
             bookingModel.setSeatNumber(Integer.parseInt(requestBody.get("seatNumber")));
             bookingModel.setPickupPoint(requestBody.get("pickUpPoint"));
             return ResponseEntity.ok(bookingService.book(bookingModel));
         }
         catch (Exception e) {
             return ErrorResponse.from("error", e.getMessage(), "message", "Booking not done");
         }

     }

     @PostMapping(path = "/cancel")
     public ResponseEntity<Object> cancelBooking(@RequestBody Map<String, String> requestBody) {
         // Verify Existence of all fields
         if (!requestBody.containsKey("bookingId")) {
             return ErrorResponse.from("error", "All fields are required");
         }

         try {
             return ResponseEntity.ok(bookingService.cancelBooking(Integer.parseInt(requestBody.get("bookingId"))));
         }
         catch (IllegalArgumentException e) {
             return ErrorResponse.from("error", e.getMessage(), "message", "Booking not cancelled");
         }

     }
}
