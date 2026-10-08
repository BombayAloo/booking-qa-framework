package com.bookingqa.api.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {

    private final String firstname;
    private final String lastname;
    private final double totalPrice;
    private final boolean depositPaid;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final String additionalNeeds;

    public Booking(String firstname, String lastname, double totalPrice, boolean depositPaid, LocalDate checkIn, LocalDate checkOut, String additionalNeeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.additionalNeeds = additionalNeeds;

        if(totalPrice <= 0 || checkIn.isAfter(checkOut) || firstname.isEmpty()) {
            throw new IllegalArgumentException("Fields are not filled correctly, please check your form");
        }
    }

    public int nights() {
        return (int) ChronoUnit.DAYS.between(checkIn, checkOut);
    }
}
