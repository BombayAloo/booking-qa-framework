package com.bookingqa.data;

import com.bookingqa.api.model.Booking;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TestDataRepositoryTest {

    private final TestDataRepository<String> repository = new TestDataRepository<>();

    @Test
    void shouldFindSavedItemByKey() {
        repository.save("user", "Jan");

        assertThat(repository.find("user")).contains("Jan");
    }

    @Test
    void shouldReturnEmptyOptionalForMissingKey() {
        assertThat(repository.find("missing")).isEmpty();
    }

    @Test
    void shouldOverwriteItemWhenSavingUnderExistingKey() {
        repository.save("user", "Jan");
        repository.save("user", "Anna");

        assertThat(repository.find("user")).contains("Anna");
        assertThat(repository.size()).isEqualTo(1);
    }

    @Test
    void shouldReturnEmptyListWhenRepositoryIsEmpty() {
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void shouldReturnAllSavedItems() {
        repository.save("first", "Jan");
        repository.save("second", "Anna");
        repository.save("third", "Piotr");

        assertThat(repository.findAll()).containsExactlyInAnyOrder("Jan", "Anna", "Piotr");
    }

    @Test
    void shouldRemoveItemByKey() {
        repository.save("user", "Jan");

        repository.remove("user");

        assertThat(repository.find("user")).isEmpty();
        assertThat(repository.size()).isZero();
    }

    @Test
    void shouldIgnoreRemovalOfMissingKey() {
        repository.save("user", "Jan");

        repository.remove("missing");

        assertThat(repository.find("user")).contains("Jan");
        assertThat(repository.size()).isEqualTo(1);
    }

    @Test
    void shouldReturnZeroSizeWhenRepositoryIsEmpty() {
        assertThat(repository.size()).isZero();
    }

    @Test
    void shouldCountSavedItems() {
        repository.save("first", "Jan");
        repository.save("second", "Anna");

        assertThat(repository.size()).isEqualTo(2);
    }

    @Test
    void shouldStoreBookings() {
        TestDataRepository<Booking> bookingRepository = new TestDataRepository<>();
        bookingRepository.save("standard", bookingFor("Jan"));

        assertThat(bookingRepository.find("standard")).contains(bookingFor("Jan"));
    }

    private static Booking bookingFor(String firstname) {
        return new Booking(firstname, "Kowalski", 300, true,
                LocalDate.of(2026, 11, 10), LocalDate.of(2026, 11, 13), "Breakfast");
    }
}