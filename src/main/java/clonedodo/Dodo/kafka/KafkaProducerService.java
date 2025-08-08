package clonedodo.Dodo.kafka;

import clonedodo.Dodo.models.dto.UserDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC = "topicMessage";

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(UserDto userDto) {
        kafkaTemplate.send(TOPIC, userDto.toString());
    }
}
