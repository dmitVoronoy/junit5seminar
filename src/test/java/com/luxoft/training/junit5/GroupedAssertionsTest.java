package com.luxoft.training.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GroupedAssertionsTest {
    @Test
    void groupedAssertions() {
        assertAll( // independent assertions
                () -> assertEquals("string", "string"),
                () -> assertTrue(1 == 1));
        assertAll( // dependent assertions
                () -> assertNotEquals("string1", "string2"),
                () -> {
                    assertNull(null);
                    assertSame(new Object(), new Object());
                });
    }
}
