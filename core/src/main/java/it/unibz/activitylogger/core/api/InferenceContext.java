package it.unibz.activitylogger.core.api;

import it.unibz.activitylogger.core.business.log_record.BasicLogRecord;
import it.unibz.activitylogger.core.business.log_record.EditableLogRecord;

public abstract class InferenceContext {
    public static InferenceContext createFrom(Input input) {
        EditableLogRecord logRecord = new BasicLogRecord();

        return new BasicInferenceContext(input, logRecord);
    }

    private final Input input;
    private final EditableLogRecord logRecord;

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

    @Override
    public String toString() {
        return "InferenceContext{" +
                "input=" + input +
                ", logRecord=" + logRecord +
                '}';
    }
}
