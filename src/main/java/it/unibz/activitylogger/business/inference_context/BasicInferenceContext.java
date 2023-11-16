package it.unibz.activitylogger.business.inference_context;

import it.unibz.activitylogger.api.Input;
import it.unibz.activitylogger.business.log_record.EditableLogRecord;

public class BasicInferenceContext extends InferenceContext {
    protected BasicInferenceContext(Input input, EditableLogRecord logRecord) {
        super(input, logRecord);
    }
}
