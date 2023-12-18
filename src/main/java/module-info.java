import it.unibz.activitylogger.core.api.Port;
import it.unibz.activitylogger.core.api.LogRecordSaver;

module it.unibz.activitylogger.main {
    requires it.unibz.activitylogger.core;
    requires it.unibz.activitylogger.inferrers;
    requires it.unibz.activitylogger.http;
    requires it.unibz.activitylogger.async;
    requires it.unibz.activitylogger.persistence;

    uses Port;
    uses LogRecordSaver;
}