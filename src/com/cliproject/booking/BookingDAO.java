package com.cliproject.booking;



public interface BookingDAO {
    int CAPACITY = 100;
    Booking[] getBookings();
    void saveBooking(Booking booking);
}
