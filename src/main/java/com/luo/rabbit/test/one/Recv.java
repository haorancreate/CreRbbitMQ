package com.luo.rabbit.test.one;

import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
import com.rabbitmq.client.QueueingConsumer;  
  
public class Recv  { 
    //��������  
    private final static String QUEUE_NAME = "queue";  
    public static void main(String[] argv) throws java.io.IOException,
    java.lang.InterruptedException  
    {  
        //�����Ӻʹ���Ƶ�����뷢�Ͷ�һ��  
        ConnectionFactory factory = new ConnectionFactory();
        //����MabbitMQ��������ip����������  
        factory.setHost("127.0.0.1");  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //�������У���ҪΪ�˷�ֹ��Ϣ�����������д˳��򣬶��л�������ʱ�������С�  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);  
        System.out.println("Waiting for messages. To exit press CTRL+C");  
          
        //��������������  
        QueueingConsumer consumer = new QueueingConsumer(channel);  
        //ָ�����Ѷ���  
        channel.basicConsume(QUEUE_NAME, true, consumer);  
        while (true)  
        {  
            //nextDelivery��һ�������������ڲ�ʵ����ʵ���������е�take������  
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
            String message = new String(delivery.getBody());  
            System.out.println("Received '" + message + "'");
        }  
  
    }  
} 
