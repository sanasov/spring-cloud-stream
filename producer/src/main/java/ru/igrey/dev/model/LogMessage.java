package ru.igrey.dev.model;

import java.io.Serializable;

public class LogMessage implements Serializable {

    private static final long serialVersionUID = -5857383701708275796L;

    private String message;
    private Integer age;

    public LogMessage() {

    }

    public LogMessage(String message, Integer age) {
        this.message = message;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message + "\t" + age;
    }

}
