package com.example.sevice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.sevice.model.Client;

@Service
public class ClientService {
	private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);
	
	
	@Autowired
    private KafkaTemplate<String, Client> kafkaTemplate;
	
	
	@Value("${app.topic.example}")
    private String topic;
	
	public void send(Client data){
		LOG.info("sending data='{}' to topic='{}'", data, topic);
		
		Message<Client> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
		
		kafkaTemplate.send(message);
		
	}

}
