package com.luxoft.training.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionAssertionsTest {
    @Test
    void exceptionAssertions() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("test");
        });
        assertEquals(exception.getMessage(), "test");
    }
}
