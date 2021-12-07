package com.luxoft.training.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AssumptionsTest {
    @Test
    void onlyInQaEnvironment() {
        assumeTrue("QA".equals(System.getenv("ENV")));
        // go further only for QA
    }

    @Test
    void combinedWithDependentAssumptions() {
        assumingThat("QA".equals(System.getenv("ENV")), () -> {
            // only for QA
            assertEquals(2, 1 + 1);
        });
        // for all environments
    }
}
