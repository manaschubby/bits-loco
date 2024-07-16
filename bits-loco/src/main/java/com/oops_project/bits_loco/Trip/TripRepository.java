package com.oops_project.bits_loco.Trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripRepository extends JpaRepository<TripModel, Integer>{
    @Query("SELECT DISTINCT pickUpPoints FROM TripModel")
    public List<String> findAllPickUpPoints();

    public List<TripModel> findAllByHostId(int hostId);
    // Find all latest trips by creation time
    public List<TripModel> findAllByOrderByCreationTimeDesc();

    List<TripModel> findAllByDestination(String destination);

    List<TripModel> findAllByDestinationAndHostId(String destination, int hostId);
}
