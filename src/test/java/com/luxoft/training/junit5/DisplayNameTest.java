package com.luxoft.training.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DisplayNameTest {
    @Test
    @DisplayName("Demonstrates that 2+2 is not 5")
    void simpleTest() {
        assertNotEquals(5, 2 + 2);
    }
}
