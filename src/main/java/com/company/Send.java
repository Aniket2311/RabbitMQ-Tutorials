package com.company;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by admin on 28-04-2018.
 */
public class Send {
    private final static String QUEUE_NAME = "Hello";

    public static void main(String[] args) throws IOException, TimeoutException {

//      todo Establishing a connection.........
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.6.211");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

//      todo Sending a message...........
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World !!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "' ");

    }
}
