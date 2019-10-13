package com.demo.spring.boot.activeMQ;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

public class AckMessageListener implements MessageListener {
    private String consumerName;
    private boolean acknowledge;
    private Session session;

    public AckMessageListener(String consumerName, boolean acknowledge,Session session) {
        this.consumerName = consumerName;
        this.acknowledge = acknowledge;
        this.session =session;
    }

    public void onMessage(Message message) {
        System.out.println(consumerName + " received " + message);

        try {
            if (acknowledge) {
                System.out.println(consumerName + " execute " + message);
                message.acknowledge();
            } else {
                Integer.parseInt("a");
            }
        } catch (JMSException e1) {
            System.out.println(e1.getMessage());
//            try {
//                session.recover();
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
        }

//        System.out.println(message);
    }
}
