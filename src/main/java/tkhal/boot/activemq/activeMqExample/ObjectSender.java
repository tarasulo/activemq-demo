package tkhal.boot.activemq.activeMqExample;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

public class ObjectSender {
    final static Logger logger = Logger.getLogger(ObjectSender.class);
    //URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is on localhost
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static Item book = new Item("book", 2019, 199.99);

    // default broker URL is : tcp://localhost:61616"
    private static String subject = "JCG_QUEUE"; // Queue Name.You can create any/many queue names as per your requirement.

    public static void main(String[] args) {
        // Getting JMS connection from the server and starting it
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

            Connection connection = connectionFactory.createConnection();
            connection.start();

            //Creating a non transactional session to send/receive JMS message.
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);

            //Destination represents here our queue 'JCG_QUEUE' on the JMS server.
            //The queue will be created automatically on the server.
            Destination destination = session.createQueue(subject);

            // MessageProducer is used for sending messages to the queue.
            MessageProducer producer = session.createProducer(destination);

            // We will send a small text message saying 'Hello World!!!'
            ObjectMessage message = session
                    .createObjectMessage(book);

            // Here we are sending our message!
            producer.send(message);

            System.out.println("JCG sending@@ '" + book + "'");
            logger.info("JCG sending@@ '" + book + "'");
            connection.close();
        } catch (JMSException e) {
            logger.error("No connection" + e);
        }

    }
}
