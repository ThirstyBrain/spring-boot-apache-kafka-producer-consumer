package com.example.springbootkafka.controllers;

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




}
