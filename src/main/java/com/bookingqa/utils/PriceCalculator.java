package com.bookingqa.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceCalculator {

    private static final int DISCOUNT_THRESHOLD_NIGHTS = 7;
    private static final BigDecimal DISCOUNT_MULTIPLIER = new BigDecimal("0.90");

    public BigDecimal calculateTotal(int pricePerNight, int nights) {
        if (nights <= 0) {
            throw new IllegalArgumentException("Number of nights must be positive, was: " + nights);
        }

        BigDecimal total = BigDecimal.valueOf(pricePerNight).multiply(BigDecimal.valueOf(nights));

        if (nights > DISCOUNT_THRESHOLD_NIGHTS) {
            total = total.multiply(DISCOUNT_MULTIPLIER);
        }

        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
