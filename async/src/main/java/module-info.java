import it.unibz.activitylogger.async.main.ActivityLoggerAsync;
import it.unibz.activitylogger.core.api.Port;

module it.unibz.activitylogger.async {
    requires it.unibz.activitylogger.core;
    requires it.unibz.activitylogger.inferrers;

    provides Port
            with ActivityLoggerAsync;

    requires com.rabbitmq.client;
    requires org.slf4j;
    requires org.slf4j.simple;
    requires com.google.gson;
}