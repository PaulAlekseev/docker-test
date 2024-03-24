package docker.example.application;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import docker.example.application.dto.AbstractDto;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class MyConfig {
    @Bean
    public ProducerFactory<String, AbstractDto> producerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
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
