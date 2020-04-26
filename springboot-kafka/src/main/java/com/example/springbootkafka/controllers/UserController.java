package com.example.springbootkafka.controllers;

import com.example.springbootkafka.listner.ConsumerListner;
import com.example.springbootkafka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RestController
@RequestMapping("/rest/user")
public class UserController {
    @Autowired
    private KafkaTemplate<String, User> _kafkaTemplate;

    private static final String TOPIC = "TestTopic";
    /*@Autowired
    private ConsumerListner _consumerListner;*/
    private User _user;

 /*   @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") final String name) {

        _kafkaTemplate.send(TOPIC, new User(name, "SW", 15000L));

        return "Message published successfully under topic "+TOPIC+" for user "+name;
    }*/

    @PostMapping("/publish")
    public User add(@RequestBody User user){
        _kafkaTemplate.send(TOPIC,user);
        return user;

    }

    @GetMapping("/consume")
    public User getAllUser(){
           return _user;
    }

    @KafkaListener(topics = "TestTopic",groupId = "group_User_json",
            containerFactory = "userKafkaListenerFactory")
    public User consumeJson(User user) {
           _user = user;
        System.out.println("Consumed message from Topic : TestTopic  and GroupID : group_User_json is : " + user);
        return user;
    }



}
