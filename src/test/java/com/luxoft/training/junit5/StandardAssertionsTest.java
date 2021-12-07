package com.luxoft.training.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StandardAssertionsTest {
    @Test
    void standardAssertions() {
        assertEquals(3, 5 - 2, "Should not fail");
        assertFalse(true, () -> "Should fail");
    }
}
