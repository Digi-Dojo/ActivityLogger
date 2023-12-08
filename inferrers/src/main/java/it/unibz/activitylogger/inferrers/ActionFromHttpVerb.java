package it.unibz.activitylogger.inferrers;

import it.unibz.activitylogger.core.api.InferenceTier;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.EditableLogRecord;

@InferenceTier(InferenceTier.Tier.LOW)
public class ActionFromHttpVerb extends BaseInferrer {
    public static final String ACTION_CREATED = "created";
    public static final String ACTION_UPDATED = "updated";
    public static final String ACTION_READ = "read";
    public static final String ACTION_UNKNOWN = "";

    @Override
    public void infer(InferenceContext context) {
        Input input = context.readInput();
        EditableLogRecord logRecord = context.getLogRecord();

        String action = mapActionFromVerb(input);
        logRecord.setAction(action);

        safeCallNext(context);
    }

    private String mapActionFromVerb(Input input) {
        return switch (input.method().toLowerCase()) {
            case Input.METHOD_POST  -> ACTION_CREATED;
            case Input.METHOD_GET   -> ACTION_READ;
            case Input.METHOD_PUT   -> ACTION_UPDATED;

            default -> ACTION_UNKNOWN;
        };
    }
}
