package com.luxoft.training.junit5.extension;

import org.junit.jupiter.api.extension.ExtensionContext;

import static com.luxoft.training.junit5.extension.ReportingExtension.END_TIME;
import static com.luxoft.training.junit5.extension.ReportingExtension.START_TIME;
import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class Reporter {
    public void reportSuccess(ExtensionContext context) {
        long millis = calculateExecutionTime(context);
        System.out.println(context.getDisplayName() + " run succeeded. Execution took " + millis + " millis");
    }

    public void reportFail(ExtensionContext context) {
        long millis = calculateExecutionTime(context);
        System.out.println(context.getDisplayName() + " run failed. Execution took " + millis + " millis");
    }

    public void reportDisabled(ExtensionContext context) {
        System.out.println(context.getDisplayName() + " run disabled.");
    }

    public void reportAborted(ExtensionContext context) {
        long millis = calculateExecutionTime(context);
        System.out.println(context.getDisplayName() + " run aborted. Execution took " + millis + " millis");
    }

    private long calculateExecutionTime(ExtensionContext context) {
        long endTime = (long) context.getStore(GLOBAL).get(END_TIME);
        long startTime = (long) context.getStore(GLOBAL).get(START_TIME);
        return endTime - startTime;
    }
}
