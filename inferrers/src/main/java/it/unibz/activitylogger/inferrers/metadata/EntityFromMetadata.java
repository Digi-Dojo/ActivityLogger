package it.unibz.activitylogger.inferrers.metadata;

import it.unibz.activitylogger.core.api.EditableLogRecord;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.Metadata;

public class EntityFromMetadata extends MetadataBasedInferrer {
    @Override
    protected void inferFromMetadata(Metadata meta, Input input, EditableLogRecord logRecord) {
        String idRef = meta.entityKey();
        String nameRef = meta.entityType();

        if (idRef != null) {
            String id = extract(idRef, input);
            logRecord.setEntityId(id);
        }

        if (nameRef != null) {
            String entityName = extract(nameRef, input);
            logRecord.setEntityName(entityName);
        }
    }
}
