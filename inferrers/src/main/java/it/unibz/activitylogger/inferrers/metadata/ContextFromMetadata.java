package it.unibz.activitylogger.inferrers.metadata;

import it.unibz.activitylogger.core.api.EditableLogRecord;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.Metadata;

public class ContextFromMetadata extends MetadataBasedInferrer {
    @Override
    protected void inferFromMetadata(Metadata meta, Input input, EditableLogRecord logRecord) {
        String idRef = meta.contextKey();
        String nameRef = meta.contextType();

        if (idRef != null) {
            String id = extract(idRef, input);
            logRecord.setContextId(id);
        }

        if (nameRef != null) {
            String contextName = extract(nameRef, input);
            logRecord.setContextName(contextName);
        }
    }

}
