package com.bookingqa.auth;

public interface Authenticator {

    String authenticate(String user, String password);
}
