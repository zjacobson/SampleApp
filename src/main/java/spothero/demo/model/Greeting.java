package spothero.demo.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name="greeting")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Greeting {
    private String message;
}
