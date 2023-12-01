package it.unibz.activitylogger.inferrers.metadata;

import it.unibz.activitylogger.core.api.EditableLogRecord;
import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.Metadata;
import it.unibz.activitylogger.inferrers.BaseInferrer;

public abstract class MetadataBasedInferrer extends BaseInferrer {
    protected abstract void inferFromMetadata(Metadata meta, EditableLogRecord logRecord);

    @Override
    public void infer(InferenceContext context) {
        Input input = context.readInput();

        if (input.hasMetadata()) {
            inferFromMetadata(input.meta(), context.getLogRecord());
        }

        safeCallNext(context);
    }
}
