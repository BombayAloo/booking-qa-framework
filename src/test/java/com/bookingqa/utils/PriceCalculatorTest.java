package com.bookingqa.utils;

import com.bookingqa.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PriceCalculatorTest extends BaseTest {

    @Test
    void shouldCalculateRegularPrice() {
        assertThat(new PriceCalculator().calculateTotal(100, 5)).isEqualByComparingTo("500");
    }

    @Test
    void shouldCalculateRegularPriceForMaxNights() {
        assertThat(new PriceCalculator().calculateTotal(100, 7)).isEqualByComparingTo("700");
    }

    @Test
    void shouldCalculateDiscount() {
        assertThat(new PriceCalculator().calculateTotal(100, 8)).isEqualByComparingTo("720");
    }

    @Test
    void shouldRejectZeroNights() {
        assertThatThrownBy(() -> new PriceCalculator().calculateTotal(100, 0)).isInstanceOf(IllegalArgumentException.class);
    }
}
