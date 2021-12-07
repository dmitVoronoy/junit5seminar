package com.luxoft.training.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class ParallelExecutionTest {
    @Test
    void test1() throws InterruptedException {
        SECONDS.sleep(5);
        System.out.println("Test 1 writing output");
    }

    @Test
    void test2() throws InterruptedException {
        SECONDS.sleep(8);
        System.out.println("Test 2 writing output");
    }

    @Test
    void test3() throws InterruptedException {
        SECONDS.sleep(13);
        System.out.println("Test 3 writing output");
    }
}
