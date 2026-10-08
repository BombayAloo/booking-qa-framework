package com.bookingqa.api.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BookingTest {

    @Test
    void shouldThrowExceptionWhenNight0OrLess() {
        assertThatThrownBy(()-> new Booking("A", "b", 0, true, LocalDate.parse("2024-12-12"), LocalDate.now(), "Nothing")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldThrowExceptionWhenCheckoutAfterCheckIn() {
        assertThatThrownBy(()-> new Booking("A", "b", 200.00, true,  LocalDate.now(), LocalDate.parse("2024-12-12"),"Nothing")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldThrowExceptionWhenNameEmpty() {
        assertThatThrownBy(()-> new Booking("", "b", 200.00, true, LocalDate.parse("2024-12-12"), LocalDate.now(), "Nothing")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldCalculateNightsBetweenDates() {
        assertThat(new Booking("A", "b", 200.00, true,  LocalDate.parse("2024-12-12"), LocalDate.parse("2025-12-12"),"Nothing").nights()).isEqualTo(365);
    }

    @Test
    void shouldCalculateOneNight() {
        assertThat(new Booking("A", "b", 200.00, true,  LocalDate.parse("2024-12-12"),LocalDate.parse("2024-12-13"),"Nothing").nights()).isEqualTo(1);
    }
}
