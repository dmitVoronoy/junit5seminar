package com.luxoft.training.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest {
    @Test
    void simpleTest() {
        assertEquals(42, 40 + 2);
    }
}
