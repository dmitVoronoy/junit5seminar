package com.luxoft.training.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NestedTest {
    @Test
    @DisplayName("has one test method")
    void test() {
    }

    @Nested
    @DisplayName("and one nested test class")
    class NestedClass {
        @Test
        @DisplayName("with one more nested test method")
        void nestedTest() {
        }
    }
}
