package com.bookingqa.auth;

public interface Authenticator {

    public String authenticate(String user, String password);

}
