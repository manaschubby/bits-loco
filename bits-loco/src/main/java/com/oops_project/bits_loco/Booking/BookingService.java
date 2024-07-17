package com.oops_project.bits_loco.Booking;

import com.oops_project.bits_loco.Trip.TripModel;
import com.oops_project.bits_loco.Trip.TripRepository;
import com.oops_project.bits_loco.User.UserModel;
import com.oops_project.bits_loco.User.UserRepository;
import com.oops_project.bits_loco.Utils.Constants.Gender;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private TripRepository tripRepository;
    private UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, TripRepository tripRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }

    public BookingModel book(BookingModel bookingModel) throws Exception{
        TripModel trip = tripRepository.findById(bookingModel.getTripId()).orElseThrow(() -> new IllegalArgumentException("Invalid trip ID"));
        if (bookingRepository.findByTripIdAndSeatNumberAndIsCancelled(bookingModel.getTripId(), bookingModel.getSeatNumber(), false) != null) {
            throw new IllegalArgumentException("Seat already booked");
        }
        if (trip.getRiders().contains(bookingModel.getRiderId())) {
            throw new IllegalArgumentException("Rider already booked");
        }
        UserModel rider = userRepository.findById(bookingModel.getRiderId()).orElseThrow(() -> new IllegalArgumentException("Invalid rider ID"));
        if (trip.getHostId() == bookingModel.getRiderId()) {
            throw new IllegalArgumentException("Host cannot book a seat");
        }
        if (trip.isFemaleOnly() && rider.getGender() != Gender.FEMALE) {
            throw new IllegalArgumentException("Trip is female only");
        }
        if (trip.getPickUpPoints().stream().noneMatch(bookingModel.getPickupPoint()::equals)) {
            throw new IllegalArgumentException("Invalid pickup point");
        }
//        if (trip.getDepartureTime().getTime() < System.currentTimeMillis()) {
//            throw new IllegalArgumentException("Trip already started");
//        }

        trip.getRiders().add(bookingModel.getRiderId());
        tripRepository.save(trip);

        return bookingRepository.save(bookingModel);

    }

    public BookingModel cancelBooking(int bookingId){
        BookingModel bookingModel = bookingRepository.findById(bookingId).orElseThrow(() -> new IllegalArgumentException("Invalid booking ID"));
        if (bookingModel.isCancelled()) {
            throw new IllegalArgumentException("Booking already cancelled");
        }
        bookingModel.setCancelled(true);
        TripModel trip = tripRepository.findById(bookingModel.getTripId()).orElseThrow(() -> new IllegalArgumentException("Invalid trip ID"));
        trip.getRiders().remove(bookingModel.getRiderId());

        tripRepository.save(trip);
        return bookingRepository.save(bookingModel);
    }

}
