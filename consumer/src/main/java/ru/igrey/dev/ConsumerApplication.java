package ru.igrey.dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import ru.igrey.dev.model.LogMessage;
import ru.igrey.dev.processor.MyProcessor;

import java.util.concurrent.atomic.AtomicInteger;


@SpringBootApplication
@EnableBinding(MyProcessor.class)
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Autowired
    private MyProcessor processor;

    private static AtomicInteger count = new AtomicInteger(0);

    @StreamListener(target = MyProcessor.INPUT)
    public void logfast(LogMessage msg) {
        if (count.incrementAndGet() <= 1) {
            System.out.println("Error message. Try count:" + count.get());
            throw new RuntimeException("Error message:" + msg);
        }
        System.out.println(msg);
    }
}
