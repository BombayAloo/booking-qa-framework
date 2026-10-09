package com.bookingqa.auth;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InMemoryAuthenticatorTest {

    @Test
    void shouldGetToken() {
        assertThat(new InMemoryAuthenticator(Map.of("admin", "password123")).authenticate("admin", "password123")).isNotEmpty();
    }

    @Test
    void shouldNotGetToken() {
        assertThatThrownBy(() -> new InMemoryAuthenticator(Map.of("admin", "password123")).authenticate("admin", "Pass")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new InMemoryAuthenticator(Map.of("admin", "password123")).authenticate("User", "password123")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new InMemoryAuthenticator(Map.of("admin", "password123")).authenticate("", "")).isInstanceOf(IllegalArgumentException.class);
    }
}
