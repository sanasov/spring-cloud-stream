package ru.igrey.dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.annotation.StreamMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.messaging.converter.MarshallingMessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
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

    @Bean
    @StreamMessageConverter
    public MessageConverter xmlConverter() {
        return new MarshallingMessageConverter(marshaller());
    }

    @Bean
    public Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(LogMessage.class);
        marshaller.setSchemas(new ClassPathResource("logMessage.xsd"));
        return marshaller;
    }
}
