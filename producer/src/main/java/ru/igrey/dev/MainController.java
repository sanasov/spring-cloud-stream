package ru.igrey.dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.igrey.dev.model.LogMessage;
import ru.igrey.dev.processor.MyProcessor;

@EnableBinding(MyProcessor.class)
@RestController
public class MainController {

    @Autowired
    MyProcessor processor;

    @GetMapping("brief")
    public String publishMessage(@RequestParam("payload") String payload) {
        processor.anOutput().send(MessageBuilder.withPayload(new LogMessage(payload)).build());
        return "send success: " + payload;
    }
}
