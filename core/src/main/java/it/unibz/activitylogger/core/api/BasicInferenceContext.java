package it.unibz.activitylogger.core.api;

import it.unibz.activitylogger.core.business.log_record.EditableLogRecord;

public class BasicInferenceContext extends InferenceContext {
    protected BasicInferenceContext(Input input, EditableLogRecord logRecord) {
        super(input, logRecord);
    }
}
