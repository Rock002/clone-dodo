package clonedodo.Dodo.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public NewTopic newTopic() {
        return new NewTopic("topicMessage", 1, (short) 1);
    }
}
