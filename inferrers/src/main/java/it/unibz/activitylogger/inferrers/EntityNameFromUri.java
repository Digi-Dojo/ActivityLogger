package it.unibz.activitylogger.inferrers;

import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.Inferrer;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.EditableLogRecord;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EntityNameFromUri implements Inferrer {
    private Inferrer next;

    @Override
    public void setNext(Inferrer inferrer) {
        this.next = inferrer;
    }

    @Override
    public void infer(InferenceContext context) {
        Input input = context.readInput();
        EditableLogRecord logRecord = context.getLogRecord();

        String entityName = Arrays.stream(input.uri().split("/"))
                .filter(slice -> !slice.matches("\\d+"))
                .collect(Collectors.joining("_"));

        logRecord.setEntityName(entityName);

        System.out.println("Inferrer: EntityNameFromUri");
        System.out.println(logRecord);

        if (this.next != null)
            this.next.infer(context);
    }
}
