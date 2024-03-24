package docker.example.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@EnableKafka
public class MyConfig {
    @KafkaListener(topics = "thing1")
    public void listen(String data) {
        System.out.println("KAFKAAAAAAAAAAAAAAAAA");
        System.out.println(data);
    }
}
