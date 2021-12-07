package com.luxoft.training.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class TimeoutTest {
    @Test
    @Timeout(10)
    void quickTest() {
    }

    @Test
    @Timeout(value = 10, unit = MICROSECONDS)
    void failingTest() throws InterruptedException {
        SECONDS.sleep(5);
    }

    @Test
    @Timeout(10)
    void poll() throws InterruptedException {
        while (dataNotAvailable()) {
            SECONDS.sleep(1);
        }
    }

    private boolean dataNotAvailable() {
        return true;
    }
}
