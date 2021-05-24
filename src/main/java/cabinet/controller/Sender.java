package cabinet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String message) {
        jmsTemplate.convertAndSend("jms/queue/test", message);
    }
}
