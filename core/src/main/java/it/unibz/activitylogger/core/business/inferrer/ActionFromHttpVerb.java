package it.unibz.activitylogger.core.business.inferrer;

import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.business.inference_context.InferenceContext;
import it.unibz.activitylogger.core.business.log_record.EditableLogRecord;

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
    }

    private String mapActionFromVerb(Input input) {
        return switch (input.method()) {
            case Input.METHOD_POST -> ACTION_CREATED;
            case Input.METHOD_GET -> ACTION_READ;
            case Input.METHOD_PUT -> ACTION_UPDATED;
            default -> ACTION_UNKNOWN;
        };
    }
}
