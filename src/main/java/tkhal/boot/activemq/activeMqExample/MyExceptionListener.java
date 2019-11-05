package tkhal.boot.activemq.activeMqExample;


import org.apache.log4j.Logger;

import javax.jms.JMSException;

public class MyExceptionListener implements javax.jms.ExceptionListener {
    final static Logger logger = Logger.getLogger(MyExceptionListener.class);

    @Override
    public void onException(JMSException e) {
    logger.error(e);
    }
}
