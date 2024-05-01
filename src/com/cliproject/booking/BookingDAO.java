package com.cliproject.booking;



public interface BookingDAO {
    int CAPACITY = 10;
    Booking[] getBookings();
    void saveBooking(Booking booking);
}
