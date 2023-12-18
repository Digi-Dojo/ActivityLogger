import it.unibz.activitylogger.core.api.Inferrer;
import it.unibz.activitylogger.core.api.LogRecordSaver;
import it.unibz.activitylogger.core.api.Port;

module it.unibz.activitylogger.core {
    exports it.unibz.activitylogger.core.api;
    exports it.unibz.activitylogger.core.main;

    uses Inferrer;
    uses Port;
    uses LogRecordSaver;
}