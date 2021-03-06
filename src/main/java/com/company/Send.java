package com.company;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * Created by admin on 28-04-2018.
 */
public class Send {
    private final static String QUEUE_NAME = "task_queue";
    private static Scanner scanner = new Scanner(System.in);

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
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

        String message = null;
        for (int i=0;i<5;i++){
            System.out.println("Enter a message....");
            message = scanner.nextLine();
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println(" [x] Sent '" + message + "' ");
        }
    }
}
