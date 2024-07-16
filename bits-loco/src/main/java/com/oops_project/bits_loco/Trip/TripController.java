package com.oops_project.bits_loco.Trip;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/trip")
public class TripController {
    TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    private Map<String, Object> getMapFromString(List<TripModel> trips, String message){
        return Map.of("message", message, "trips", trips);
    }

    @GetMapping(path = "")
    public ResponseEntity<Object> getTrips(@RequestParam Optional<Integer> hostId, @RequestParam Optional<String> destination){

        if (hostId.isPresent() && destination.isPresent()){
            return ResponseEntity.ok(getMapFromString(tripService.getTrips(destination.get(), hostId.get()), "Trips fetched successfully"));
        }
        if (hostId.isPresent()){
            return ResponseEntity.ok(getMapFromString(tripService.getTrips(hostId.get()), "Trips fetched successfully"));
        }
        if (destination.isPresent()){
            return ResponseEntity.ok(getMapFromString(tripService.getTrips(destination.get()), "Trips fetched successfully"));
        }
        return ResponseEntity.ok(tripService.getTrips().toString());
    }

    @PostMapping(path = "")
    public ResponseEntity<Object> addTrip(@RequestBody Map<String, String> trip){
        // Verify Existence of all fields
        if (!trip.containsKey("hostId")){
            return ResponseEntity.badRequest().body(Map.of("error", "Host ID is required"));
        }
        if (!trip.containsKey("destination")){
            return ResponseEntity.badRequest().body(Map.of("error", "Destination is required"));
        }
        if (!trip.containsKey("pickUpPoints")){
            return ResponseEntity.badRequest().body(Map.of("error", "Pick Up Points are required"));
        }
        if (!trip.containsKey("tripTime")){
            return ResponseEntity.badRequest().body(Map.of("error", "Trip Time is required"));
        }
        if (!trip.containsKey("tripDate")){
            return ResponseEntity.badRequest().body(Map.of("error", "Trip Date is required"));
        }
        if (!trip.containsKey("tripType")){
            return ResponseEntity.badRequest().body(Map.of("error", "Trip Type is required"));
        }
        if (!trip.containsKey("tripStatus")){
            return ResponseEntity.badRequest().body(Map.of("error", "Trip Status is required"));
        }
        try {
            return ResponseEntity.ok(tripService.addTrip(trip));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

//    @GetMapping(path = "/pickUpPoints")
//    public String getPickUpPoints(){
//        return tripService.getPickUpPoints().toString();
//    }


}
