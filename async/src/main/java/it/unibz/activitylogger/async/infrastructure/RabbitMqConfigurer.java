package it.unibz.activitylogger.async.infrastructure;

import it.unibz.activitylogger.async.api.Publisher;
import it.unibz.activitylogger.async.api.Callback;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqConfigurer {
    public static RabbitMqConfigurer create() {
        return new RabbitMqConfigurer();
    }

    private final ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private String channelName;
    private String queue;
    private String routingKey;

    private RabbitMqConfigurer() {
        this.factory = new ConnectionFactory();
    }

    public RabbitMqConfigurer connectTo(String host) {
        factory.setHost(host);
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public RabbitMqConfigurer setTopicName(String name) {
        try {
            this.channelName = name;
            channel = connection.createChannel();
            channel.exchangeDeclare(name, "topic");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public RabbitMqConfigurer setKey(String key) {
        this.routingKey = key;

        return this;
    }

    public RabbitMqConfigurer bindToQueueWithKey(String key) {
        try {
            queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, channelName, key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public RabbitMqConfigurer reactMessagesWith(Callback callback) {
        DeliverCallback deliverCallback = (consumerTag, rawMessage) -> {
            String message = new String(rawMessage.getBody(), "UTF-8");
            callback.run(message);
        };

        try {
            channel.basicConsume(queue, true, deliverCallback, consumerTag -> {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public Publisher getPublisher() {
        return new BasicPublisher(channelName, channel, routingKey);
    }
}

