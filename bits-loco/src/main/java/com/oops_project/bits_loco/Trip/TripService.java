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

    public Object addTrip(Map<String, Object> trip) throws Exception {
        TripModel tripModel = new TripModel();
        tripModel.setHostId(Integer.parseInt(trip.get("hostId").toString()));
        tripModel.setDestination(trip.get("destination").toString());
        List<String> pickUpPoints = ((List<String>) trip.get("pickUpPoints"));
        tripModel.setPickUpPoints(pickUpPoints);
        Date departureTime = new Date();
        departureTime.setTime(Long.parseLong(trip.get("departureTime").toString()));
        Date eta = new Date();
        eta.setTime(Long.parseLong(trip.get("eta").toString()));
        tripModel.setDepartureTime(departureTime);
        tripModel.setEta(eta);
        tripModel.setFemaleOnly(((boolean) trip.get("femaleOnly")));
//        tripRepository.save(tripModel);
        System.out.println(tripModel);
        Map<String, Object> requestPriceOptions= (Map<String, Object>) trip.get("priceOptions");
        for (Map.Entry<String, Object> entry : requestPriceOptions.entrySet()) {
            Object value = entry.getValue();
            double doubleValue;
            if (value instanceof Integer) {
                doubleValue = ((Integer) value).doubleValue();
            } else if (value instanceof Double) {
                doubleValue = (Double) value;
            } else {
                throw new IllegalArgumentException("Value must be an instance of Integer or Double");
            }
            tripModel.getPriceOptions().put(Integer.parseInt(entry.getKey()), doubleValue);
        }
        return Map.of("message", "Trip added successfully", "trip", tripModel);
    }
}
