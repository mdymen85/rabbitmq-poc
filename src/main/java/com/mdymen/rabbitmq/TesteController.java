package com.mdymen.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private QueueSender queueSender;

    @Autowired
    private AmqpTemplate queueSender2;

    @GetMapping
    public String send(){
        queueSender.send("test message");
        return "ok. done";
    }

    @GetMapping
    public String send2(){

        String mensagem = "test message";

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("ultima", "sim");
        Message message = new Message(mensagem.getBytes(), messageProperties);

        queueSender2.convertAndSend("teste-exchange", "routing-key-teste", message);
        return "ok. done";
    }

}