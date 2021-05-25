/*
package cabinet.controller;

import javax.jms.Destination;
import javax.jms.JMSProducer;
import javax.naming.Context;
import javax.naming.NamingException;

public class Sender {

    private static final String DEFAULT_DESTINATION = "jms/queue/test";

    private final JMSProducer producer;
    private final Destination testQueue;

    public Sender(final JMSProducer producer, final Context namingContext) throws NamingException {
        this.producer = producer;
        testQueue = (Destination) namingContext.lookup(DEFAULT_DESTINATION);
    }

    public void send(String message) {
        producer.send(testQueue, message);
    }
}
*/

