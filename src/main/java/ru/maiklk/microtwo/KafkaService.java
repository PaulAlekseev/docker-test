package ru.maiklk.microtwo;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class KafkaService {



    private final KafkaTemplate<Integer, String> template;

    public void sendToKafka() throws ExecutionException, InterruptedException, TimeoutException {
            template.send("thing1", 123, "Niggers").get(10, TimeUnit.SECONDS);
    }


    @KafkaListener(topics = "thing1", groupId = "MY_GROUP_ID")
    public void listen(String data) {
        System.out.println(data);
    }
}
