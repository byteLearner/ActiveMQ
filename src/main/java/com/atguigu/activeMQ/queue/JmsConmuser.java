package com.atguigu.activeMQ.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsConmuser {
    public static  final  String ACTIVEMQ_URL = "tcp://192.168.121.3:61616";
    public static final String QUEUE_NAME = "queue01";
    public static void main(String[] args) throws Exception {
        //1创建连接工厂，按照给定的url地址，采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2通过连接工厂，获得连接connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        //3创建会话session
        //两个参数，第一个事务/第二个签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4创建目的地（具体是队列还是主题topic）
        Queue queue01 = session.createQueue(QUEUE_NAME);
        //5创建消费者
        MessageConsumer consumer = session.createConsumer(queue01);
        /*while (true) {
            TextMessage receive = (TextMessage) consumer.receive();
            if (receive != null) {
                System.out.println("消费者接收到消息"+receive.getText());
            }else {
                break;
            }
        }
        consumer.close();
        session.close();
        connection.close();*/
        //设置监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if (null != message&&message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage)message;
                    try {
                        System.out.println("***消费者接收消息"+textMessage.getText());
                    }catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
