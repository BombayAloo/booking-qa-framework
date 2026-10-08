package com.bookingqa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public abstract class BaseTest {

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        System.out.println("Currently executing test is: " + testInfo.getDisplayName());
    }
}
