package it.unibz.activitylogger.inferrers;

import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.InferenceTier;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.EditableLogRecord;

import java.util.Arrays;
import java.util.stream.Collectors;

@InferenceTier(InferenceTier.Tier.MEDIUM)
public class EntityNameFromUri extends BaseInferrer {
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

        safeCallNext(context);
    }
}
