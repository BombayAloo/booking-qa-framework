package com.bookingqa.utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    static <T> T randomElement(List<T> list) {
        if (list.isEmpty()) throw new IllegalArgumentException("List must not be empty");

        int index = ThreadLocalRandom.current().nextInt(list.size());
        return list.get(index);
    }
}
