package com.bookingqa.utils;

public class PriceCalculator {

    public double calculateTotal(int pricePerNight, int nights) {
        if(nights >7) {
            return pricePerNight * nights * 0.9;
        } else if (nights <= 0) {
            throw new IllegalArgumentException();
        } else {
            return pricePerNight * nights;
        }
    }
}
