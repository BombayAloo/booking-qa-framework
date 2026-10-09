package com.bookingqa.auth;

import java.util.Map;
import java.util.UUID;

public class InMemoryAuthenticator implements Authenticator {

    private final Map<String, String> users;

    public InMemoryAuthenticator(Map<String, String> users) {
        this.users = users;
    }

    @Override
    public String authenticate(String userName, String userPassword) {
        if (!users.containsKey(userName) || !users.get(userName).equals(userPassword)) {
            throw new IllegalArgumentException("Invalid credentials for user: " + userName);
        }
        return UUID.randomUUID().toString();
    }
}
