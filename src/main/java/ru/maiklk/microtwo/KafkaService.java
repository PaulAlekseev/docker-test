package ru.maiklk.microtwo;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class KafkaService {



    private final KafkaTemplate<Integer, String> template;
    private final WebsocketHandler websocketHandler;

    public void sendToKafka() throws ExecutionException, InterruptedException, TimeoutException {
            template.send("thing1", 123, "Niggers").get(10, TimeUnit.SECONDS);
    }


    @KafkaListener(topics = "thing1", groupId = "MY_GROUP_ID")
    public void listen(String data) {
        try {
            websocketHandler.sendToAll(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(data);
    }
}
