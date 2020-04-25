package com.example.springbootkafka.listner;

import com.example.springbootkafka.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerListner {

    @KafkaListener(topics = "TestTopic",groupId = "group_User_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(User user) {
        System.out.println("Consumed message from Topic : TestTopic  and GroupID : group_User_json is : " + user);
    }
}
