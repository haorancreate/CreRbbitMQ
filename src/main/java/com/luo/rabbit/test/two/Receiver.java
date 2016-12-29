package com.luo.rabbit.test.two;

import java.io.IOException;
import org.apache.commons.lang.SerializationUtils;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Receiver extends BaseConnector implements Runnable, Consumer {
	
	public Receiver(String queueName) throws IOException {
		super(queueName);
	}
	
	//ʵ��Runnable��run����
	public void run() {
		 try {
			channel.basicConsume(queueName, true,this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ������Щ��������ʵ��Consumer�ӿڵ�
	 **/	
	//��������ע������Զ�����
	public void handleConsumeOk(String consumerTag) {
		System.out.println("Consumer "+consumerTag +" registered");
	}
	//�������߽��յ���Ϣ���Զ�����
	public void handleDelivery(String consumerTag, Envelope env,
	            BasicProperties props, byte[] body) throws IOException {
		MessageInfo messageInfo = (MessageInfo)SerializationUtils.deserialize(body);
        System.out.println("Message ( "
        		+ "channel : " + messageInfo.getChannel() 
        		+ " , content : " + messageInfo.getContent() 
        		+ " ) received.");
         
    }
	//������Щ����������ʱ�������
	public void handleCancelOk(String consumerTag) {
	}
	public void handleCancel(String consumerTag) throws IOException {
	}
	public void handleShutdownSignal(String consumerTag,
			ShutdownSignalException sig) {
	}
	public void handleRecoverOk(String consumerTag) {
	}
}
