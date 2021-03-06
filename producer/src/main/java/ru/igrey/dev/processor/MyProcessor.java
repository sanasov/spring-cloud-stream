package ru.igrey.dev.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyProcessor {

    @Input
    SubscribableChannel myInput();

    @Output("myBinding")
    MessageChannel anOutput();

    @Output
    MessageChannel anotherOutput();
}
