package clonedodo.DodoAlert.kafka;

import clonedodo.DodoAlert.socket.WebSocketController;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class KafkaConsumerService {

    private final WebSocketController webSocketController;
    private final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    public KafkaConsumerService(WebSocketController webSocketController) {
        this.webSocketController = webSocketController;
    }

    @KafkaListener(topics = "topicMessage", groupId = "messagesConsumer")
    public void listener(String order) {
        System.out.println(order.trim());
        log.info(order);
        webSocketController.sendKafkaMessage(order);
    }
}
