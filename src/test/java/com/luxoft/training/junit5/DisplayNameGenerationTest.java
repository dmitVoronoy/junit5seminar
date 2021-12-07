package com.luxoft.training.junit5;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DisplayNameGenerationTest {
    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class A_string_is_a_palindrome {
        @Test
        void if_it_reads_the_same_backward_and_forward() {
        }
    }
}
