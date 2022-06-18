package com.example.week4.rabbit.producer;
import com.example.week4.entity.Advertisement;
import com.example.week4.entity.User;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {

    @Value("${spring.rabbit.routing.name}")
    private String routingName;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Queue queue;
    public void sendToQueue (User user) {

        System.out.println("User Creation sent to queue...");
        rabbitTemplate.convertAndSend(queue.getName());

    }

    public void sendToQueue (Advertisement advertisement) {

        System.out.println("Advertisement Creation sent to queue...");
        rabbitTemplate.convertAndSend(queue.getName());

    }

}
