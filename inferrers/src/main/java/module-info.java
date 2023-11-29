import it.unibz.activitylogger.core.api.Inferrer;
import it.unibz.activitylogger.inferrers.ActionFromHttpVerb;
import it.unibz.activitylogger.inferrers.ActionInUri;
import it.unibz.activitylogger.inferrers.ContextNameAndIdFromUri;
import it.unibz.activitylogger.inferrers.EntityNameFromUri;

module it.unibz.activitylogger.inferrers {
    requires it.unibz.activitylogger.core;

    provides Inferrer
            with EntityNameFromUri,
                    ActionFromHttpVerb,
                    ActionInUri,
                    ContextNameAndIdFromUri
            ;
}