package com.bookingqa.api.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

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
            throw new IllegalArgumentException("Fields are not filled correctly, price should be more than 0.");
        } else if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Fields are not filled correctly, check-out must be after check-in.");
        } else if (firstname.isBlank()) {
            throw new IllegalArgumentException("Fields are not filled correctly, first name can not be empty.");
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Double.compare(totalPrice, booking.totalPrice) == 0 && depositPaid == booking.depositPaid && Objects.equals(firstname, booking.firstname) && Objects.equals(lastname, booking.lastname) && Objects.equals(checkIn, booking.checkIn) && Objects.equals(checkOut, booking.checkOut) && Objects.equals(additionalNeeds, booking.additionalNeeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, totalPrice, depositPaid, checkIn, checkOut, additionalNeeds);
    }
}
