package ru.maiklk.microtwo;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MyController {

    private final KafkaService kafkaService;

    @GetMapping("/testFortnite")
    public ResponseEntity<String> testFortnite() throws ExecutionException, InterruptedException, TimeoutException {
        kafkaService.sendToKafka();
        return ResponseEntity.ok("Message sent");
    }
}
