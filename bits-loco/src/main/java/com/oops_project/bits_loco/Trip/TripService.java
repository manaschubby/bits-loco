package com.oops_project.bits_loco.Trip;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TripService {
    TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<TripModel> getTrips() {
        return tripRepository.findAll();
    }

    public List<TripModel> getTrips(int hostId) {
        return tripRepository.findAllByHostId(hostId);
    }

    public List<TripModel> getTrips(String destination) {
        return tripRepository.findAllByDestination(destination);
    }

    public List<TripModel> getTrips(String destination, int hostId) {
        return tripRepository.findAllByDestinationAndHostId(destination, hostId);
    }

    public Object addTrip(Map<String, String> trip) {
        TripModel tripModel = new TripModel();
        tripModel.setHostId(Integer.parseInt(trip.get("hostId")));
        tripModel.setDestination(trip.get("destination"));
        List<String> pickUpPoints = List.of(trip.get("pickUpPoints").substring(1, trip.get("pickUpPoints").length() - 1).split(","));
        tripModel.setPickUpPoints(pickUpPoints);
        Date departureTime = new Date();
        departureTime.setTime(Long.parseLong(trip.get("tripTime")));
        tripModel.setTripDate(trip.get("tripDate"));
        tripRepository.save(tripModel);
        return Map.of("message", "Trip added successfully", "trip", tripModel);
    }
}
