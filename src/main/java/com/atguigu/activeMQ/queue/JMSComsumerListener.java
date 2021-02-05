package com.atguigu.activeMQ.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

/**
 * @description: 异步消息监听
 * @author: liuqiang
 * @time: 2021/2/3 10:52
 */
public class JMSComsumerListener {
    private static final String ACTIVEMQ_URL = "tcp://192.168.121.3:61616";
    private static final  String ACTIVEMQ_QUEUE="queue01";

    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(ACTIVEMQ_QUEUE);
        MessageConsumer messageConsumer = session.createConsumer(queue);
        /*messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message != null && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println("监听到消息"+textMessage);
                }
            }
        });*/
        messageConsumer.setMessageListener(message ->  {
                if (message != null && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println("监听到消息"+textMessage);
                }
        });

    }
}
