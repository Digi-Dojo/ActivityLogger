module it.unibz.activitylogger.async {
    requires it.unibz.activitylogger.core;
    requires it.unibz.activitylogger.inferrers;

    requires com.rabbitmq.client;
    requires org.slf4j;
    requires org.slf4j.simple;
}