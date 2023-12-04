import it.unibz.activitylogger.core.api.Inferrer;
import it.unibz.activitylogger.inferrers.*;
import it.unibz.activitylogger.inferrers.metadata.*;

module it.unibz.activitylogger.inferrers {
    requires it.unibz.activitylogger.core;

    provides Inferrer
            with EntityNameFromUri,
                    ActionFromHttpVerb,
                    ActionInUri,
                    ContextNameAndIdFromUri,
                    ActionFromMetadata,
                    ContextFromMetadata,
                    EntityFromMetadata;
}