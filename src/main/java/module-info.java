import it.unibz.activitylogger.core.api.Port;

module it.unibz.activitylogger.main {
    requires it.unibz.activitylogger.core;
    requires it.unibz.activitylogger.inferrers;
    requires it.unibz.activitylogger.http;
    requires it.unibz.activitylogger.async;

    uses Port;
}