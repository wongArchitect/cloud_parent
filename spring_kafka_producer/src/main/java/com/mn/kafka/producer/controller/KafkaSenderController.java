package com.mn.kafka.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KafkaSenderController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/sendMsg")
    public String sendMsg() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            log.info("---------------第" + (i + 1) + "条信息已发送。。。-----------------");
            kafkaTemplate.send("kafkatest123", "test_" + i, "value_" + i * 10);
            Thread.sleep(2000);
        }
        return "message send success";
    }

//    @KafkaListener(topics = "kafkatest123")
//    public void receive(ConsumerRecord<String, Object> consumer) {
//        log.info("消息已消费：topic为[{}]-key为[{}]-value为[{}]", consumer.topic(), consumer.key(), consumer.value());
//    }
}