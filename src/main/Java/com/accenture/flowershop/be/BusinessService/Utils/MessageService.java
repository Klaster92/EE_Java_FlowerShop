package com.accenture.flowershop.be.BusinessService.Utils;

import com.accenture.flowershop.be.BusinessService.User.UserBusinessService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;
import javax.xml.rpc.ServiceException;
import java.io.BufferedReader;
import java.io.FileReader;

@Service
public class MessageService {

    @Autowired
    private Marshalling marshalling;
    @Autowired
    private UserBusinessService userBusinessService;

    private ConnectionFactory connectionFactory = null;
    private Connection connection = null;
    private Session session = null;
    private MessageProducer messageProducer = null;
    private MessageConsumer messageConsumer = null;

    @Value("${exportPath}")
    String exportPath;

    @PostConstruct
    private void postConstruct() {
        try {
            String url = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
            connectionFactory = new ActiveMQConnectionFactory(url);
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue inQueue = session.createQueue("IN_QUEUE");
            Queue outQueue = session.createQueue("OUT_QUEUE");
            connection.start();
            messageProducer = session.createProducer(outQueue);
            messageConsumer = session.createConsumer(inQueue);
            messageConsumer.setMessageListener(new MessageListener() {
                /*Принимаем все запросы на изменение скидки пользователя*/
                @Override
                public void onMessage(Message message) {

                    try {
                        TextMessage textMessage = (TextMessage) message;
                        String body = textMessage.getText();
                        DiscountRequest request = (DiscountRequest) marshalling.doUnMarshalling(body);
                        userBusinessService.updateDiscount(request.getIdUser(), request.getDiscount());
                    } catch (JMSException e) {
                        throw new RuntimeException("FAIL");
                    } catch (com.accenture.flowershop.be.BusinessService.Utils.ServiceException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            preDestroy();
        }
    }

    @PreDestroy
    private void preDestroy() {
        try {
            connection.close();
            session.close();
            messageProducer.close();
            messageConsumer.close();
        } catch (JMSException e) {
            throw new RuntimeException("FAIL");
        }
    }

    /*Отпавить xml файл юзера в очередь*/
    public void sendUserXml(String file) {
        //try-with-resources Java7
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(exportPath + file))) {
            String line;
            String text = "";
            while ((line = bufferedReader.readLine()) != null) {
                text += line;
            }
            Message msg = session.createTextMessage(text);
            messageProducer.send(msg);

        } catch (Exception e) {
            throw new RuntimeException("FAIL");
        }
    }

}