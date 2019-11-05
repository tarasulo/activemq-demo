package tkhal.boot.activemq.activeMqExample;

import org.apache.log4j.Logger;

import javax.jms.*;

public class ConsumerMessageListener implements MessageListener {
    final static Logger logger = Logger.getLogger(ConsumerMessageListener.class);
    private String consumerName;

    public ConsumerMessageListener(String consumerName) {
        this.consumerName = consumerName;
    }

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            System.out.println(consumerName + " received "
                    + objectMessage.getObject().toString());
            logger.info(consumerName + " received "
                    + objectMessage.getObject().toString());
        } catch (JMSException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }
}
