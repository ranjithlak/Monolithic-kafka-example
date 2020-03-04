package com.example.sevice.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.sevice.model.Client;


@Configuration
public class ProducerKafkaConfiguration {

	@Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
	
	@Bean
	public ProducerFactory<String,Client> producerFactory(){
		
		Map<String,Object> producerConfigProperties = new HashMap<>();
		producerConfigProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
		producerConfigProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
		producerConfigProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(producerConfigProperties);
		
	}
	
	@Bean
	public KafkaTemplate<String,Client> kafkaTemplate(){
		return new KafkaTemplate<String,Client>(producerFactory());
		
	}
}
