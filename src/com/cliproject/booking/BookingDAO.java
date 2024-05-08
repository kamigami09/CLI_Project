package com.cliproject.booking;


import java.util.ArrayList;

public interface BookingDAO {
    ArrayList<Booking> getBookings();
    void saveBooking(Booking booking);
}
