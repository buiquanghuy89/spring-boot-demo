package com.demo.spring.boot.activeMQ;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.RedeliveryPolicy;

import javax.jms.*;
import java.util.Date;

/**
 * Created by HuyBQ on 9/22/2019.
 */
public class JmsProducerAckExample {
    public static void main(String[] args) {
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
            redeliveryPolicy.setInitialRedeliveryDelay(3000);
            redeliveryPolicy.setBackOffMultiplier(1.1);
            redeliveryPolicy.setMaximumRedeliveryDelay(20000);
            redeliveryPolicy.setUseExponentialBackOff(false);
            redeliveryPolicy.setMaximumRedeliveries(3);/**/

            Queue queue = session.createQueue("customerQueue");
            MessageProducer producer = session.createProducer(queue);
            for (int i = 0; i < 10; i++) {
                String payload = "Important Task " + new Date().getTime();
                Message msg = session.createTextMessage(payload);
                System.out.println("Sending text '" + payload + "'");
                producer.send(msg);
                Thread.sleep(10000);
            }

            Thread.sleep(10000);
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
