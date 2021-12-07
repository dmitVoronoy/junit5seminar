package com.luxoft.training.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.readString;
import static java.nio.file.Files.write;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TempDirExtensionTest {
    @Test
    void tempDir(@TempDir Path tempDirectory) throws IOException {
        Path file = tempDirectory.resolve("tempDirTest.txt");
        write(file, "temp content".getBytes());
        assertEquals("temp content", readString(file));
        System.out.println("Temp directory path" + file.toAbsolutePath());
    }
}
