package com.luxoft.training.junit5;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TagsTest {
    @Test
    @Tag("fast")
    void fastTest() {
    }

    @Test
    @Tag("integration")
    void integrationTest() {
    }
}
