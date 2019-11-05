package tkhal.boot.activemq.activeMqExample;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class ObjectReceiver {
    final static Logger logger = Logger.getLogger(ObjectSender.class);
    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // default broker URL is : tcp://localhost:61616"

    // Name of the queue we will receive messages from
    private static String subject = "JCG_QUEUE";

    public static void main(String[] args) throws JMSException {

        try {
            // Getting JMS connection from the server
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

            Connection connection = connectionFactory.createConnection();

            // Creating session for receiving messages
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);

            // Getting the queue 'JCG_QUEUE'
            Destination destination = session.createQueue(subject);

            // MessageConsumer is used for receiving (consuming) messages
            MessageConsumer consumer = session.createConsumer(destination);
            ObjectMessage message = (ObjectMessage) consumer.receive();
            if (message instanceof ObjectMessage) {
                ObjectMessage objectMessage = message;
                System.out.println("Received message '" + objectMessage.toString() + "'");
                logger.info(("Received message '" + objectMessage.toString() + "'"));
            }
            connection.close();
        } catch (JMSException e) {
            logger.error("Can't receive message" + e);
        }
    }
}
