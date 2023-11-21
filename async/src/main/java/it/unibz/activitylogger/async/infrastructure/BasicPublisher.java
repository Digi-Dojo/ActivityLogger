package it.unibz.activitylogger.async.infrastructure;

import com.rabbitmq.client.Channel;
import it.unibz.activitylogger.async.api.Publisher;

import java.io.IOException;

public class BasicPublisher implements Publisher {
    private final String channelName;
    private final Channel channel;
    private final String routingKey;

    public BasicPublisher(String channelName, Channel channel, String routingKey) {
        this.channelName = channelName;
        this.channel = channel;
        this.routingKey = routingKey;
    }

    @Override
    public void publish(String message) {
        try {
            channel.basicPublish(channelName, routingKey, null, message.getBytes("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
