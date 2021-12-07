package com.luxoft.training.junit5;

import com.luxoft.training.junit5.extension.ReportingExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(ReportingExtension.class)
public class ReportingExtensionTest {
    @Test
    void success() {
    }

    @Test
    void failure() {
        fail("Intended fail");
    }

    @Test
    void aborted() {
        assumeTrue(false);
    }

    @Test
    @Disabled
    void disabled() {
    }
}
