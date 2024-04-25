package com.cliproject.booking;


public class BookingArrayDataAccessService implements BookingDAO{
    private static Booking[] bookings;
    private static int nextAvailableSpot = 0;
    private static final int CAPACITY = 5;

    static {
        bookings = new Booking[CAPACITY];
    }

    @Override
    public void saveBooking(Booking booking){
        if (nextAvailableSpot + 1 >= CAPACITY){
//            grow db
        }
        else {
            bookings[nextAvailableSpot++] = booking;
        }
    }
    @Override
    public Booking[] getBookings(){return bookings;}
}
