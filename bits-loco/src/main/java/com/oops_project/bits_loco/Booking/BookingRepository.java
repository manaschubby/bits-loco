package com.oops_project.bits_loco.Booking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingModel, Integer> {
    public BookingModel findByTripIdAndRiderId(int tripId, int riderId);
    public BookingModel findByTripIdAndRiderIdAndIsCancelled(int tripId, int riderId, boolean isCancelled);
    public BookingModel findByTripIdAndSeatNumber(int tripId, int seatNumber);
    public BookingModel findByTripIdAndSeatNumberAndIsCancelled(int tripId, int seatNumber, boolean isCancelled);
    public BookingModel findByTripIdAndRiderIdAndSeatNumber(int tripId, int riderId, int seatNumber);
    public BookingModel findByTripIdAndRiderIdAndSeatNumberAndIsCancelled(int tripId, int riderId, int seatNumber, boolean isCancelled);
    public BookingModel findByTripIdAndSeatNumberAndIsCancelledAndRiderIdNot(int tripId, int seatNumber, boolean isCancelled, int riderId);

}
