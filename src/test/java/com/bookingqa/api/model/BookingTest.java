package com.bookingqa.api.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BookingTest {

    @Test
    void shouldThrowExceptionWhenPrice0OrLess() {
        assertThatThrownBy(() -> new Booking("A", "b", 0, true, LocalDate.parse("2024-12-12"), LocalDate.parse("2024-12-13"), "Nothing")).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("price");
    }

    @Test
    void shouldThrowExceptionWhenCheckInAfterCheckout() {
        assertThatThrownBy(() -> new Booking("A", "b", 200.00, true, LocalDate.parse("2024-12-13"), LocalDate.parse("2024-12-12"), "Nothing")).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("check");
    }

    @Test
    void shouldThrowExceptionWhenCheckInAndCheckoutSameDay() {
        assertThatThrownBy(() -> new Booking("A", "b", 200.00, true, LocalDate.parse("2024-12-13"), LocalDate.parse("2024-12-13"), "Nothing")).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("check");
    }

    @Test
    void shouldThrowExceptionWhenNameEmpty() {
        assertThatThrownBy(() -> new Booking("", "b", 200.00, true, LocalDate.parse("2024-12-12"), LocalDate.parse("2024-12-13"), "Nothing")).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("name");
    }

    @Test
    void shouldCalculateNightsBetweenDates() {
        assertThat(new Booking("A", "b", 200.00, true, LocalDate.parse("2024-12-12"), LocalDate.parse("2025-12-12"), "Nothing").nights()).isEqualTo(365);
    }

    @Test
    void shouldCalculateOneNight() {
        assertThat(new Booking("A", "b", 200.00, true, LocalDate.parse("2024-12-12"), LocalDate.parse("2024-12-13"), "Nothing").nights()).isEqualTo(1);
    }

    @Test
    void shouldTreatIdenticalBookingsAsOneInSet() {
        Booking first = new Booking("Jan", "Kowalski", 300, true,
                LocalDate.of(2026, 11, 10), LocalDate.of(2026, 11, 13), "Breakfast");
        Booking second = new Booking("Jan", "Kowalski", 300, true,
                LocalDate.of(2026, 11, 10), LocalDate.of(2026, 11, 13), "Breakfast");

        Set<Booking> bookings = new HashSet<>();
        bookings.add(first);
        bookings.add(second);

        assertThat(bookings).hasSize(1);
    }
}
