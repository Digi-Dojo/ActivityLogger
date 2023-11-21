import it.unibz.activitylogger.core.api.Port;
import it.unibz.activitylogger.http.ActivityLoggerHttp;

module it.unibz.activitylogger.http {
    requires it.unibz.activitylogger.core;
    requires it.unibz.activitylogger.inferrers;

    provides Port
            with ActivityLoggerHttp;

    requires io.javalin;
    requires com.fasterxml.jackson.core;
    requires org.slf4j;
}