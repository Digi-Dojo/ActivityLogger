package it.unibz.activitylogger.inferrers.metadata;

import it.unibz.activitylogger.core.api.EditableLogRecord;
import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.Metadata;
import it.unibz.activitylogger.inferrers.BaseInferrer;

import java.util.regex.Matcher;

public abstract class MetadataBasedInferrer extends BaseInferrer {
    protected static final String REFERENCE_REGEX = "^\\{(.+)@(.+)\\}$";
    protected Matcher matcher;

    protected abstract void inferFromMetadata(Metadata meta, Input input, EditableLogRecord logRecord);

    @Override
    public void infer(InferenceContext context) {
        Input input = context.readInput();

        if (input.hasMetadata()) {
            inferFromMetadata(input.meta(), input, context.getLogRecord());
        }

        safeCallNext(context);
    }
}
