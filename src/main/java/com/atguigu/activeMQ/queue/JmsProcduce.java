package com.atguigu.activeMQ.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProcduce  {
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
        //5创建消息生产者
        MessageProducer messageProducer = session.createProducer(queue01);
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//消息非持久化
       // messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);//消息持久化//Queue默认是持久化的
        //6通过使用messageProcducer生产3条消息发送到MQ的队列里面
        for (int i = 1;i <= 3; i++ ) {
            //7创建消息
            TextMessage textMessage = session.createTextMessage("msg----" + i);
            //8通过messageprocducer发送mq
            messageProducer.send(textMessage);
        }
        //9关闭资源
        messageProducer.close();
        session.close();
        connection.close();

    }
}
