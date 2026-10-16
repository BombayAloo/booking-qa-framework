package com.bookingqa.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TestDataRepository<T> {

    Map<String, T> objects = new HashMap<>();

    public void save(String key, T item) {
        objects.put(key, item);
    }

    public Optional<T> find(String key) {
        return Optional.ofNullable(objects.get(key));
    }

    public List<T> findAll() {
        return objects.values().stream().toList();
    }

    public void remove(String key) {
        objects.remove(key);
    }

    public int size() {
        return objects.size();
    }
}
