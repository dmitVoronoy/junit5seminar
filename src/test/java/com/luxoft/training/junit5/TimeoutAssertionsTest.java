package com.luxoft.training.junit5;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class TimeoutAssertionsTest {
    @Test
    void timeoutWithResult() {
        String result = assertTimeout(Duration.ofMinutes(5), () -> "result");
        assertEquals("result", result);
    }

    @Test
    void timeoutWithPreemptiveTermination() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            new CountDownLatch(1).await();
        });
    }
}
