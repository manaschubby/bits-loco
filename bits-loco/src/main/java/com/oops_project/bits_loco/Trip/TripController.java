package com.oops_project.bits_loco.Trip;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<Object> addTrip(@RequestBody Map<String, Object> trip){
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
        if (!trip.containsKey("departureTime")){
            return ResponseEntity.badRequest().body(Map.of("error", "Departure Time is required"));
        }
        if (!trip.containsKey("eta")){
            return ResponseEntity.badRequest().body(Map.of("error", "Trip ETA is required"));
        }
        if (!trip.containsKey("femaleOnly")){
            return ResponseEntity.badRequest().body(Map.of("error", "FEMALE only is required"));
        }
        if (!trip.containsKey("priceOptions")){
            return ResponseEntity.badRequest().body(Map.of("error", "Price Options are required"));
        }
        if (!(trip.get("priceOptions") instanceof Map<?,?>)){
            return ResponseEntity.badRequest().body(Map.of("error", "Price Options should be a map"));
        }
        try {
            System.out.println(((List<String>) trip.get("pickUpPoints")).get(0));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("error", "Pick Up Points should be a list"));
        }


        try {
            return ResponseEntity.ok(tripService.addTrip(trip));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

//    @GetMapping(path = "/pickUpPoints")
//    public String getPickUpPoints(){
//        return tripService.getPickUpPoints().toString();
//    }


}
