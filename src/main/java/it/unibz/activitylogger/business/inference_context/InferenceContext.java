package it.unibz.activitylogger.business.inference_context;

import it.unibz.activitylogger.api.Input;
import it.unibz.activitylogger.business.log_record.BasicLogRecord;
import it.unibz.activitylogger.business.log_record.EditableLogRecord;

public abstract class InferenceContext {
    public static InferenceContext createFrom(Input input) {
        EditableLogRecord logRecord = new BasicLogRecord();

        return new BasicInferenceContext(input, logRecord);
    }

    private final Input input;
    private EditableLogRecord logRecord;

    protected InferenceContext(Input input, EditableLogRecord logRecord) {
        this.input = input;
        this.logRecord = logRecord;
    }

    public Input readInput() {
        return this.input;
    }

    public EditableLogRecord getLogRecord() {
        return this.logRecord;
    }
}
