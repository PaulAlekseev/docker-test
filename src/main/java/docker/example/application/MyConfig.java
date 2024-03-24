package docker.example.application;

import docker.example.application.dto.AbstractDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class MyConfig {


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ProducerFactory<String, AbstractDto> producerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        //Kafka сама решает когда создавать новые партиции, на основе ключа или нагрузки
        properties.put(ProducerConfig.PARTITIONER_ADPATIVE_PARTITIONING_ENABLE_CONFIG, true);
//        Можно реализовать Partitioner из пакета org.apache.kafka.clients.producer и каждая партиция
//        будет со своей логикой
//        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG)
//        по умолчанию ключ сообщения кафки является партицией
//        properties.put(ProducerConfig.PARTITIONER_IGNORE_KEYS_CONFIG)
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, AbstractDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
