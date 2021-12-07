package com.luxoft.training.junit5;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatedExecutionsTest {
    @RepeatedTest(value = 5, name = "This is {currentRepetition} repetition of " + "{totalRepetitions} repetitions")
    void repeatedTest() {
        assertEquals(2, 1 + 1);
    }
}
