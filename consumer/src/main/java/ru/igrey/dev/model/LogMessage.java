package ru.igrey.dev.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class LogMessage implements Serializable {

    private static final long serialVersionUID = -5857383701708275796L;

    @XmlElement
    private String message;

    public LogMessage() {

    }

    public LogMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return getClass().toString() + message;
    }

}
