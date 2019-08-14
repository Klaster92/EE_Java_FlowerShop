package com.accenture.flowershop.be.BusinessService.Marshalling;

import com.accenture.flowershop.be.Entity.User.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class MarshallingServiceImpl {

    public void convertObjectToXml(User user) {
        try {
            JAXBContext context = JAXBContext.newInstance(User.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(user, new File(""));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
