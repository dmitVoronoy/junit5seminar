package com.luxoft.training.junit5.extension;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class ReportingExtension implements BeforeEachCallback, AfterEachCallback, TestWatcher {
    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTime";

    private final Reporter reporter = new Reporter();

    @Override
    public void beforeEach(ExtensionContext context) {
        context.getStore(GLOBAL).put(START_TIME, currentTimeMillis());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        context.getStore(GLOBAL).put(END_TIME, currentTimeMillis());
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        reporter.reportSuccess(context);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        reporter.reportFail(context);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        reporter.reportDisabled(context);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        reporter.reportAborted(context);
    }
}
