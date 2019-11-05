package tkhal.boot.activemq.activeMqExample;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

public class MessageReceiver {

    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // default broker URL is : tcp://localhost:61616"

    // Name of the queue we will receive messages from
    private static String subject = "JCG_QUEUE";

    public static void main(String[] args) throws JMSException, InterruptedException {

        // Getting JMS connection from the server
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        // the eternal cycle for receiving random string messages
        Connection connection = null;
//        try {
            connection = connectionFactory.createConnection();

            // Creating session for receiving messages
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);

            // Getting the queue 'JCG_QUEUE'
            Destination destination = session.createQueue(subject);

            // MessageConsumer is used for receiving (consuming) messages
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new ConsumerMessageListener("Consumer"));
            connection.start();
            Thread.sleep(1000);
            session.close();
//        } finally {
//            if (connection != null) {
//                connection.close();
//            }
//        }
        // Here we receive the message.
        //Message message = consumer.receive();

        // We will be using TestMessage in our example. MessageProducer sent us a TextMessage
        // so we must cast to it to get access to its .getText() method.
//            if (message instanceof TextMessage) {
//                TextMessage textMessage = (TextMessage) message;
//                System.out.println("Received message '" + textMessage.getText() + "'");
//            }
//            connection.close();

    }
}
