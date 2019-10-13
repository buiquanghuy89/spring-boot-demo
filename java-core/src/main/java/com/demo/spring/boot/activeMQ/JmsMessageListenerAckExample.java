package com.demo.spring.boot.activeMQ;

import java.net.URI;
import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

public class JmsMessageListenerAckExample {
    public static void main(String[] args) throws URISyntaxException, Exception {
//        BrokerService broker = BrokerFactory.createBroker(new URI(
//                "broker:(tcp://localhost:61616)"));
//        broker.start();
        ActiveMQConnection connection = null;
        try {
            // Producer
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
                    "tcp://localhost:61616");
            connection = (ActiveMQConnection) factory.createConnection();
            ActiveMQSession session = (ActiveMQSession) connection.createSession(false,
                    Session.CLIENT_ACKNOWLEDGE);
            RedeliveryPolicy redeliveryPolicy = connection.getRedeliveryPolicy();
            //redeliveryPolicy.setBackOffMultiplier();
            redeliveryPolicy.setInitialRedeliveryDelay(5000);
            redeliveryPolicy.setBackOffMultiplier(1.1);
            redeliveryPolicy.setMaximumRedeliveryDelay(20000);
            redeliveryPolicy.setUseExponentialBackOff(false);
            redeliveryPolicy.setMaximumRedeliveries(3);/**/

//            Queue queue = session.createQueue("customerQueue");
//            String payload = "Important Task";
//            Message msg = session.createTextMessage(payload);
//            MessageProducer producer = session.createProducer(queue);
//
//            System.out.println("Sending text '" + payload + "'");
//            producer.send(msg);

            // Consumer
            MessageConsumer consumer = session.createConsumer(session.createQueue("customerQueue"));
            consumer.setMessageListener(
                    new AckMessageListener("customer2", true, session));
//            MessageConsumer consumer2 = session.createConsumer(session.createQueue("customerQueue"));
//            consumer2.setMessageListener(
//                    new AckMessageListener("customer2", true));
            connection.start();

            Thread.sleep(100000);
//
//            System.out.println("Change the message listener to acknowledge");
//            System.out.println("Sending text '" + payload + "'");
//            producer.send(msg);

            Thread.sleep(100000);
            session.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
//            broker.stop();
        }
    }

}