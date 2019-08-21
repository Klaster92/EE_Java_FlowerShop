package com.accenture.flowershop.be.BusinessService.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

@Service
public class Marshalling {

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    @Value("${exportPath}")
    String exportPath;

    public Marshalling() {}


    public void doMarshaling(String fileName, Object object) throws IOException {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(exportPath + fileName);
            marshaller.marshal(object, new StreamResult(fileWriter));

        } finally {
            fileWriter.close();
        }
    }

    public Object doUnMarshalling(String file) {

        try {
            unmarshaller.supports(DiscountRequest.class);
            return unmarshaller.unmarshal(new StreamSource(new StringReader(file)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }
}