package ru.maiklk.microtwo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.maiklk.microtwo.dto.IndividualDto;
import ru.maiklk.microtwo.dto.MessageDto;
import ru.maiklk.microtwo.service.KafkaService;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final KafkaService kafkaService;
    @Value("${kafka_server}")
    private String kafkaServerHost;

    @PostMapping("/sendIndividual")
    public ResponseEntity<String> sendMessage(@RequestBody IndividualDto dto) {
        kafkaService.send(dto);
        return new ResponseEntity<>("Individual send", HttpStatus.OK);
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDto dto) {
        kafkaService.send(dto);
        return new ResponseEntity<>(String.format("Successfully autowired: %s", kafkaServerHost), HttpStatus.OK);
    }
}
