import it.unibz.activitylogger.core.api.Inferrer;

module it.unibz.activitylogger.core {
    exports it.unibz.activitylogger.core.api;
    exports it.unibz.activitylogger.core.main;

    uses Inferrer;
}