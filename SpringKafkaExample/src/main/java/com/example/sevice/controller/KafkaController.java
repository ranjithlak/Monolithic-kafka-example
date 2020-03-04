package com.example.sevice.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sevice.model.Client;
import com.example.sevice.repositiory.ClientRepositiory;
import com.example.sevice.service.ClientService;

@RestController
@RequestMapping("/api/auth")
public class KafkaController {
	
	 private static final Logger LOG = LoggerFactory.getLogger(KafkaController.class);
	
	@Autowired
    private ClientService sender;
	
	@Autowired
	private ClientRepositiory clientRepositiory;
	
	
	
	@PostMapping("/create-client")
	public ResponseEntity<?> createClient(@Valid @RequestBody Client client) {
		ResponseEntity<?> responseMessage = null;
		try {
			Client newClient=new Client(client.getName(),client.getEmail(),client.getCity());
			sender.send(newClient);
			responseMessage = new ResponseEntity<>("Client send MesageQueue sucessfully", HttpStatus.OK);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return responseMessage;
	}
	
	@KafkaListener(topics = "${app.topic.example}",containerFactory = "kafkaListenerContainerFactory")
    public void receive(@Payload Client data,
                        @Headers MessageHeaders headers) {
        LOG.info("received data='{}'", data);

        headers.keySet().forEach(key -> {
            LOG.info("{}: {}", key, headers.get(key));
        });
        clientRepositiory.saveAndFlush(data);
    }

}
