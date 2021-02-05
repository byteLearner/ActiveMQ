package com.atguigu.activeMQ.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: liuqiang
 * @time: 2021/2/2 21:29
 */
@Service
public class SpringMQ_Consumer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        SpringMQ_Consumer consumer = (SpringMQ_Consumer) applicationContext.getBean("springMQ_Consumer");
        String receive = (String) consumer.jmsTemplate.receiveAndConvert();
        System.out.println("消费者收到消息"+receive);
    }
}
