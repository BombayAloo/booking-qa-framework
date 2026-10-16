package com.bookingqa.learning;

import com.bookingqa.api.model.Booking;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled("Pomiar wydajności – uruchamiany ręcznie, nie jest testem produktu")
class CollectionPerformanceTest {

    private static final int COLLECTION_SIZE = 10_000;
    private static final int WARMUP_ROUNDS = 5;
    private static final int MEASURED_CALLS = 1_000;

    @Test
    void compareContainsPerformance() {
        List<Booking> bookingList = new ArrayList<>();
        Set<Booking> bookingSet = new HashSet<>();

        for (int i = 0; i < COLLECTION_SIZE; i++) {
            Booking booking = bookingWithName("Jan" + i);
            bookingList.add(booking);
            bookingSet.add(booking);
        }

        assertThat(bookingList).hasSize(COLLECTION_SIZE);
        assertThat(bookingSet).hasSize(COLLECTION_SIZE);

        Map<String, Booking> searchedBookings = new LinkedHashMap<>();
        searchedBookings.put("początek", bookingWithName("Jan0"));
        searchedBookings.put("koniec", bookingWithName("Jan" + (COLLECTION_SIZE - 1)));
        searchedBookings.put("brak", bookingWithName("Nieistniejący"));

        System.out.printf("Rozmiar kolekcji: %,d%n", COLLECTION_SIZE);
        searchedBookings.forEach((label, searched) -> {
            double listNanos = measureContains(bookingList, searched);
            double setNanos = measureContains(bookingSet, searched);
            System.out.printf("%-10s ArrayList: %,12.0f ns | HashSet: %,6.0f ns | %,.1fx%n",
                    label, listNanos, setNanos, listNanos / setNanos);
        });
    }


    private static Booking bookingWithName(String firstname) {
        return new Booking(firstname, "Kowalski", 300, true,
                LocalDate.of(2026, 11, 10), LocalDate.of(2026, 11, 13), "Breakfast");
    }

    private static double measureContains(Collection<Booking> collection, Booking searched) {
        for (int round = 0; round < WARMUP_ROUNDS; round++) {
            callContains(collection, searched);
        }

        long start = System.nanoTime();
        int found = callContains(collection, searched);
        long elapsed = System.nanoTime() - start;

        assertThat(found).isIn(0, MEASURED_CALLS);
        return (double) elapsed / MEASURED_CALLS;
    }

    private static int callContains(Collection<Booking> collection, Booking searched) {
        int found = 0;
        for (int call = 0; call < MEASURED_CALLS; call++) {
            if (collection.contains(searched)) {
                found++;
            }
        }
        return found;
    }
}
