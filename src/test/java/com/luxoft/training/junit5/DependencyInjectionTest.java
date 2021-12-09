package com.luxoft.training.junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DependencyInjectionTest {
    @Test
    @Tag("info")
    @DisplayName("Test with injected TestInfo")
    void testInfo(TestInfo testInfo) {
        assertEquals("Test with injected TestInfo", testInfo.getDisplayName());
        assertTrue(testInfo.getTags().contains("info"));
    }

    @Test
    @DisplayName("Test with injected TestReporter")
    void testReporter(TestReporter testReporter) {
        testReporter.publishEntry("Inside test reporter");
    }

    @RepeatedTest(10)
    void repeatedTest(RepetitionInfo repetitionInfo) {
        assertEquals(10,
                repetitionInfo.getTotalRepetitions());
    }
}
