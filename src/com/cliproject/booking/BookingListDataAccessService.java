package com.cliproject.booking;


import java.util.ArrayList;

public class BookingListDataAccessService implements BookingDAO{
    private static ArrayList<Booking> bookings = new ArrayList<>();


    @Override
    public void saveBooking(Booking booking){
        bookings.add(booking);
    }

    @Override
    public ArrayList<Booking> getBookings(){
        return bookings;
    }
}
