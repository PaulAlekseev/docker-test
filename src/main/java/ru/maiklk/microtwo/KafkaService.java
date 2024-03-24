package ru.maiklk.microtwo;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class KafkaService {


    private KafkaTemplate<Integer, String> template;

    public void sendToKafka() throws ExecutionException, InterruptedException, TimeoutException {
            template.send("thing1", "Niggers").get(10, TimeUnit.SECONDS);
    }
}
