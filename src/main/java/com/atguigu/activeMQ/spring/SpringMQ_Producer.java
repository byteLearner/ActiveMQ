package com.atguigu.activeMQ.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @description:
 * @author: Andy
 * @time: 2021/2/2 21:30
 */
@Service
public class SpringMQ_Producer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        SpringMQ_Producer producer = (SpringMQ_Producer) applicationContext.getBean("springMQ_Producer");
       /* producer.jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("******springMQ");
                return textMessage;
            }
        });*/
        producer.jmsTemplate.send((session) -> {
            TextMessage textMessage = session.createTextMessage("******springMQ");
            return textMessage;
        });
        System.out.println("*******over");
    }
}
