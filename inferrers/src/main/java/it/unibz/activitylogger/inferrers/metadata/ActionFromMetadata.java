package it.unibz.activitylogger.inferrers.metadata;

import it.unibz.activitylogger.core.api.EditableLogRecord;
import it.unibz.activitylogger.core.api.Metadata;

public class ActionFromMetadata extends MetadataBasedInferrer {
    @Override
    protected void inferFromMetadata(Metadata meta, EditableLogRecord logRecord) {
        String actionMetadata = meta.action();

        if (actionMetadata != null)
            logRecord.setAction(actionMetadata);
    }
}
