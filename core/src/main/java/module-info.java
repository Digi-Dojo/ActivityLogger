import it.unibz.activitylogger.core.api.Inferrer;

module it.unibz.activitylogger.core {
    exports it.unibz.activitylogger.core.api;
    exports it.unibz.activitylogger.core.main;

    provides Inferrer
            with it.unibz.activitylogger.core.business.inferrer.ActionFromHttpVerb;

    uses Inferrer;
}