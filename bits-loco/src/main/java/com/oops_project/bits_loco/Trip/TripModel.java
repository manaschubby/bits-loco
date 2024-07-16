package com.oops_project.bits_loco.Trip;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "trips")
public class TripModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tripId;

    @Column
    private int hostId;

    @Column
    private String destination;

    @Column
    private Date departureTime;

    @Column
    @CreationTimestamp
    private Date creationTime;

    @Column
    private Date eta;

    @ElementCollection
    @CollectionTable(name = "price_options", joinColumns = @JoinColumn(name = "trip_id"))
    @MapKeyColumn(name = "num_users")
    @Column(name = "price")
    private Map<Integer, Double> priceOptions; // Key: Number of users, Value: Price

    @ElementCollection
    @CollectionTable(name = "riders", joinColumns = @JoinColumn(name = "trip_id"))
    private List<Integer> riders;

    @ElementCollection
    @CollectionTable(name = "pick_up_points", joinColumns = @JoinColumn(name = "trip_id"))
    private List<String> pickUpPoints;

    @Column
    private String otp;

    @Column
    private boolean femaleOnly;

    public TripModel() {
        this.priceOptions = new HashMap<>();
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
    }

    public Map<Integer, Double> getPriceOptions() {
        return priceOptions;
    }

    public void setPriceOptions(Map<Integer, Double> priceOptions) {
        this.priceOptions = priceOptions;
    }

    public List<Integer> getRiders() {
        return riders;
    }

    public void setRiders(List<Integer> riders) {
        this.riders = riders;
    }

    public List<String> getPickUpPoints() {
        return pickUpPoints;
    }

    public void setPickUpPoints(List<String> pickUpPoints) {
        this.pickUpPoints = pickUpPoints;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean isFemaleOnly() {
        return femaleOnly;
    }

    public void setFemaleOnly(boolean femaleOnly) {
        this.femaleOnly = femaleOnly;
    }
}