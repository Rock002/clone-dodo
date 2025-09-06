package clonedodo.Dodo.kafka;

import clonedodo.Dodo.models.dto.UserDto;
import clonedodo.Dodo.models.entity.Food;
import clonedodo.Dodo.models.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC = "topicMessage";

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user) {
        StringBuilder builder = new StringBuilder();
        builder.append(user.getUsername());
        builder.append(' ');
        List<Food> foodList = user.getListOfFood();
        for (Food food : foodList) {
            builder.append(food);
            builder.append(' ');
        }
        kafkaTemplate.send(TOPIC, builder.toString());
    }
}
