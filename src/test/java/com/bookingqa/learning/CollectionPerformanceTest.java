package com.bookingqa.learning;

import com.bookingqa.api.model.Booking;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionPerformanceTest {

    @Test
    //@Disabled
    void whatFasterArrayOrHashSet() {
        List<Booking> bookingList = new ArrayList<>();
        Set<Booking> bookingsHashSet = new HashSet<>();
        for (int i =0; i<10000; i++) {
            bookingsHashSet.add(new Booking("Jan" + i, "Kowalski", 300, true,
                    LocalDate.of(2026, 11, 10), LocalDate.of(2026, 11, 13), "Breakfast"));
            bookingList.add(new Booking("Jan"+ i, "Kowalski", 300, true,
                    LocalDate.of(2026, 11, 10), LocalDate.of(2026, 11, 13), "Breakfast"));
        }

        if(bookingList.size() != 10000 || bookingsHashSet.size() != 10000)  throw new IllegalArgumentException("Collection have wrong sizes");


    }
}
