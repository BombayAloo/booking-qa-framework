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
        if (totalPrice <= 0) {
            throw new IllegalArgumentException("Fields are not filled correctly, price should be more than 0");
        } else if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Fields are not filled correctly, check in is after check out");
        } else if (firstname.isBlank()) {
            throw new IllegalArgumentException("Fields are not filled correctly, first name can not be empty");
        }

        this.firstname = firstname;
        this.lastname = lastname;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.additionalNeeds = additionalNeeds;
    }

    public int nights() {
        return (int) ChronoUnit.DAYS.between(checkIn, checkOut);
    }
}
