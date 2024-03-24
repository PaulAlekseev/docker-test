package docker.example.application.service;

import docker.example.application.dto.AbstractDto;
import docker.example.application.dto.AbstractDtoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, AbstractDto> kafkaIndividual;

    public void send() {
        kafkaIndividual.send("Niggers", new AbstractDtoImpl("niggers"));
    }
}
