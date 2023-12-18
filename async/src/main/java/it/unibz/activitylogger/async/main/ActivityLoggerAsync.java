package it.unibz.activitylogger.async.main;

import it.unibz.activitylogger.async.business.InputParser;
import it.unibz.activitylogger.async.business.consumers.InputConsumer;
import it.unibz.activitylogger.core.api.LogRecordSaver;
import it.unibz.activitylogger.core.api.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibz.activitylogger.async.api.Publisher;
import it.unibz.activitylogger.async.infrastructure.RabbitMqConfigurer;

import java.util.Properties;

public class ActivityLoggerAsync implements Port {
    private LogRecordSaver logRecordSaver;

    @Override
    public void run(Properties config) {
        Logger logger = LoggerFactory.getLogger(ActivityLoggerAsync.class);

        String host = config.getProperty("activitylogger.async.rabbitmq_host");
        String topicName = config.getProperty("activitylogger.async.topic_name");
        String routingKey = topicName;

        InputConsumer inputConsumer = new InputConsumer(new InputParser(), this.logRecordSaver);

        RabbitMqConfigurer.create()
                .connectTo(host)
                .setTopicName(topicName)
                .bindToQueueWithKey(routingKey)
                .reactMessagesWith(inputConsumer);


        publishTest(host, topicName, routingKey, logger);
    }

    @Override
    public void setLogRecordSaver(LogRecordSaver logRecordSaver) {
        this.logRecordSaver = logRecordSaver;
    }

    private void publishTest(String host, String topicName, String routingKey, Logger logger) {
        Publisher publisher = RabbitMqConfigurer.create()
                .connectTo(host)
                .setTopicName(topicName)
                .setKey(routingKey)
                .getPublisher();

        logger.info("producer configured");

        publisher.publish("{\n"                                     +
                "  \"method\": \"PUT\",\n"                          +
                "  \"uri\": \"/users/567/profile/settings\",\n"     +
                "  \"body\": {\n"                                   +
                "    \"settings\": {\n"                             +
                "      \"notifications\": {\n"                      +
                "        \"email\": true,\n"                        +
                "        \"push\": false\n"                         +
                "      },\n"                                        +
                "      \"theme\": \"dark\",\n"                      +
                "      \"language\": \"en_US\",\n"                  +
                "      \"preferences\": {\n"                        +
                "        \"show_avatars\": true,\n"                 +
                "        \"auto_play_videos\": false\n"             +
                "      }\n"                                         +
                "    }\n"                                           +
                "  }\n"                                             +
                "}");
    }
}
