package retranslator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import retranslator.config.Client;

@SpringBootApplication
public class KafkaApp implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger("KafkaApp");

    @Autowired
    private Client client;

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;

    public static void main(String[] args) {
        SpringApplication.run(KafkaApp.class, args);
    }

    @Override
    public void run(String... strings) {
        LOG.info("Listening message to topic: {}...", topicName);
    }

    @KafkaListener(topics = "messages", groupId = "test")
    public void listen(String message) {
        LOG.info("Received message: {}", message);
        client.forward(message);
    }

}
